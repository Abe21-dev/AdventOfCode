package com.example.Day3

import com.example.readFileToString
import kotlin.collections.sorted
import kotlin.math.pow





fun main() {
    val input = readFileToString("src/main/kotlin/Day3/Day3Part2.txt").split("\n").map { it.map { it.digitToInt() } }
    val digits = 12
    var picks = ""
    var res = 0L
    for (row in input) {
        var i = 0
        // find max exculidng 12-1
        for (j in 1..12) {
            val subsection = row.size - (digits - 1 - picks.length)
            val subarray = row.subList(i, subsection)
            val curr = subarray.max()
            picks += curr.toString()
            i +=subarray.indexOf(curr)+1
        }
        println(picks)
        res += picks.toLong()
        picks = ""
    }
    println(res)
}