package com.example.Day6

import com.example.readFileToString


fun main() {
    val input = readFileToString("src/main/kotlin/Day6/Day6Sample.txt").split("\n")
        .map { it.split(" ").filter { arr -> !arr.isEmpty() } }.filter { !it.isEmpty() }
    println(input)
    var res = 0L
    for (i in 0..<input[0].size){
        val numb = mutableListOf<Long>()
        for (j in 0..<input.size){
            if (input[j][i] !="*" && input[j][i] != "+"){
                numb.add(input[j][i].toLong())
            }
            else if (input[j][i] == "*"){
                res += numb.fold(1L) { acc, value -> acc*value}
            }
            else{
                res += numb.fold(0L) { acc, value -> acc+value}
            }
        }
    }
    println(res)
}