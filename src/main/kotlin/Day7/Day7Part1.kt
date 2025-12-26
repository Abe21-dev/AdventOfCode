package com.example.Day7

import com.example.readFileToString
import java.util.LinkedList


/**
 * Thought process
 * breath first search where you start at point s and only enqueue the point directly bellow the current one
 * when dequeuing if you encounter a ^ you split and enqueue currunet [row i+1][column + 1] and [row i+1][column - 1]
 * then count that as a split
 */

fun main() {
    val input = readFileToString("src/main/kotlin/Day7/Day7Test.txt").split("\n").map { it.split("").toMutableList() }
    val q = LinkedList<Pair<Int, Int>>()
    var numOfSplit = 0
    // find the first value
    q.add(0 to input[0].indexOf("S"))
    val seenIdx = mutableSetOf<Pair<Int, Int>>()
    val splitIdx = mutableSetOf<Pair<Int, Int>>()
    while (!q.isEmpty()) {
        val idx = q.poll()

        if (idx.first !in 0..<input.size || idx.second !in 0..<input.size) {
            continue
        }
        visualizeInput(input)
        if (input[idx.first][idx.second] == "^") {
            splitIdx.add(idx)
            // will add logic
            val leftIdx = idx.first to idx.second - 1
            val rightIdx = idx.first to idx.second + 1
            if(leftIdx !in seenIdx) {
                q.add(leftIdx)
                seenIdx.add(leftIdx)
            }
            if(rightIdx !in seenIdx) {
                q.add(rightIdx)
                seenIdx.add(rightIdx)
            }
        } else {
            if (input[idx.first][idx.second] != "S") {
                input[idx.first][idx.second] = "|"
            }
            val newIdx = idx.first + 1 to idx.second
            if (newIdx !in seenIdx) {
                q.add(newIdx)
                seenIdx.add(newIdx)
            }
        }
    }
    println(splitIdx.size)
}


fun visualizeInput(input: List<List<String>>) {
    val visualize = StringBuilder("")
    println("====================")
    for (row in input) {
        for (c in row) {
            print(c)
        }
        println()
    }
}