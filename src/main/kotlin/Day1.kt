package com.example

import kotlin.math.abs
import kotlin.math.sign


fun main() {
    val input = readFileToString("src/main/kotlin/Day1.txt")
    val moves = input.split("\n")

    var i = 50
    var res = 0
    var cross = 0
    for (m in moves) {
        var offset = m.substring(1).toInt()
        var sign  = if(m[0]=='L') -1 else 1
        print(m)

        for(j in 0..offset-1){
            i+=sign*1
            if (i < 0) {
                i = 99
            } else if (i > 99) {
                i = 0
            }

            if(i==0 && j!= offset-1){
                print("\t pass")
                res++
            }
        }
        println("\t$i")
        if(i==0){
            res++
        }
    }
    println(res)
}