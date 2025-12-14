package com.example.Day1

import com.example.readFileToString


fun main() {
    val input = readFileToString("src/main/kotlin/Day1/Day1.txt")
    val moves = input.split("\n")

    var i = 50
    var res = 0
    for (m in moves) {
        val offset = m.substring(1).toInt()
        val sign  = if(m[0]=='L') -1 else 1
        for(j in 0..<offset){
            i+=sign
            if (i < 0) {
                i = 99
            } else if (i > 99) {
                i = 0
            }
            if(i==0){
                res++
            }
        }
    }
    println(res)
}