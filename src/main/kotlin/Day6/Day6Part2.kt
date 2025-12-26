package com.example.Day6

import com.example.readFileToString
import kotlin.math.max

fun main() {
    var input = readFileToString("src/main/kotlin/Day6/Day6Input.txt").split("\n")
    println(input)
    var maxLen = 0
    for (row in input) {
        maxLen = max(row.length, maxLen)
    }
    input = input.map {
        if (it.length != maxLen) {
            println("short ${it.length}")
            it + "x".repeat(maxLen - it.length)
        } else {
            println("fit ${it.length}")
            it
        }
    }
//    println(input)
    var res = 0L
    var numberStack = mutableListOf<Long>()
    var currNum = ""
    var operation = ""
    for(i in input[0].length-1 downTo 0){
        for (j in 0..<input.size){
            val c = input[j][i]
            if (c != 'x'){
                if(c=='*' || c=='+'){
                    operation = c.toString()
                }
                else if (c!=' '){
                    currNum+=c.toString()
                }
            }
            if(c == ' ' && !operation.isEmpty()) {
//                println("Operations $operation")
                if (operation == "*") {
                    res += numberStack.fold(1L) {acc, num -> acc *num}
                }
                else{
                    res += numberStack.fold(0L) {acc, num -> acc+num}
                }
                numberStack.clear()
                operation = ""
            }
        }
        if(!currNum.isEmpty()) {
//            println(currNum)
            numberStack.add(currNum.toLong())
            currNum = ""
        }
    }
    if(!operation.isEmpty()) {
//        println("Operations $operation")
        if (operation == "*") {
            res += numberStack.fold(1L) {acc, num -> acc *num}
        }
        else{
            res += numberStack.fold(0L) {acc, num -> acc+num}
        }
        numberStack.clear()
        operation = ""
    }
    println(res)
}