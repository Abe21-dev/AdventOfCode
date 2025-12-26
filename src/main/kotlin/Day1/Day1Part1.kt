package com.example.Day1

import com.example.readFileToString


fun main() {
    val input = readFileToString("src/main/kotlin/Day1/Day1Sample.txt")
    val moves = input.split("\n")

    var i = 50
    var res = 0
    for (m in moves) {
        val offset = m.substring(1).toInt()
        val sign  = if(m[0]=='L') -1 else 1
        i+=sign*offset
        if (i%100 == 0){
            println(i)
            res++
        }
    }
    println(res)
}