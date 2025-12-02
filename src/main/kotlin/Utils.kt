package com.example

import java.io.File


fun readFileToString(filePath: String) : String{
    return File(filePath).readText()
}