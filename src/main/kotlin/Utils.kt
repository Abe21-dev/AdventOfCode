package com.example

import java.io.File
import kotlin.math.pow
import kotlin.math.sqrt


fun readFileToString(filePath: String) : String{
    return File(filePath).readText()
}

fun distance3D(a: Triple<Int, Int, Int>, b: Triple<Int, Int, Int>) : Double{
    val x = (b.first-a.first).toDouble()
    val y = (b.second-a.second).toDouble()
    val z = (b.third-a.third).toDouble()
    return sqrt(x.pow(2)+y.pow(2)+z.pow(2))
}


