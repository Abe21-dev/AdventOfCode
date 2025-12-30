package com.example.Day8

import com.example.distance3D
import com.example.readFileToString

fun main(){
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
    val seen = mutableSetOf<Point>()
    var res = 0L
    for((d, pair) in distance) {
        seen.add(pair.first)
        seen.add(pair.second)
        if (seen.size == input.size){
            res = pair.first.first.toLong() *pair.second.first
            break
        }
    }
    println(res)
}
