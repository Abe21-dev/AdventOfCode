package com.example.Day1

import com.example.readFileToString


fun main() {
    val input = readFileToString("src/main/kotlin/Day1/Day1Sample.txt")
    val moves = input.split("\n")

    var currLocation = 50
    var res = 0
    for (m in moves) {
        val offset = m.substring(1).toInt()
        println(m)
        val sign  = if(m[0]=='L') -1 else 1
        res += offset/100
        var startLocation = currLocation

        currLocation += sign*(offset%100)
        if( startLocation !=0 && (currLocation !in 0..100)){
            res++
        }

        currLocation %= 100;
        println("start: $startLocation curr: $currLocation")

        if (currLocation < 0) {
            currLocation += 100;
        }

        if (startLocation != 0 && currLocation == 0) {
            res++;
        }

        println("=============")
    }
    println(res)
}