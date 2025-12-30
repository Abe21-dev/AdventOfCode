package com.example.Day8

import com.example.distance3D
import com.example.readFileToString
import java.math.BigInteger

typealias Point = Triple<Int, Int, Int>
typealias Edge = Pair<Point, Point>
typealias ScoredEdge = Pair<Double, Edge>

fun main() {
    val input = readFileToString("src/main/kotlin/Day8/Day8Input.txt").lines().map { line ->
        val (x, y, z) = line.split(",").map { it.toInt() }
        Triple(x, y, z)
    }

    val measured = mutableSetOf<Point>()
    val distance = mutableListOf<ScoredEdge>()
    for (s in input){
        for (p in input){
            if (p !in measured && p!= s){
                distance.add(distance3D(s, p) to (s to p))
            }
        }
        measured.add(s)
    }

    distance.sortBy { it.first }
    val cluster = mutableListOf<MutableSet<Point>>()
    var connections = 1000
    var i = 0
    val a: Point? = null
    val b: Point? = null

    for((d, pair) in distance.take(connections)) {
        if(i>=distance.size){
            break
        }
        var firstCluster: MutableSet<Point>? = null
        var secondCluster: MutableSet<Point>? = null

        for (c in cluster){
            if (pair.first in c){
                firstCluster = c
            }
            else if(pair.second in c){
                secondCluster = c
            }
        }
        when {
            firstCluster != null && secondCluster != null && firstCluster != secondCluster -> {
                firstCluster.addAll(secondCluster)
                cluster.remove(secondCluster)
            }
            firstCluster != null -> firstCluster.add(pair.second)
            secondCluster != null -> secondCluster.add(pair.first)
            else -> cluster.add(mutableSetOf(pair.first, pair.second))
        }
        connections--
        i++
    }
    cluster.sortByDescending { it.size }
    val res = cluster[0].size.toLong() * cluster[1].size * cluster[2].size
    println(res)
}

fun printClusters(cluster: List<Set<Point>>) {
    cluster.forEachIndexed { i, points ->
        println("Cluster $i (${points.size} points):")
        points.forEach { (x, y, z) ->
            println("  ($x, $y, $z)")
        }
    }
}