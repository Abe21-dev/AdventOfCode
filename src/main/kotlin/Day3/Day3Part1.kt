package com.example.Day3

import com.example.readFileToString
import kotlin.math.max


fun main() {
    val input  = readFileToString("src/main/kotlin/Day3/Day3Part1.txt").split("\n")
    var res = 0
    for (row in input){
        var max = 0
        for (i in 0..<row.length){
            for (j in i+1..<row.length){
                var curr ="${row[i]}${row[j]}".toInt()
                max = max(max, curr)
            }
        }
        res +=max
        max = 0
    }
    println(res)
}