package com.example.Day5

import com.example.readFileToString
import kotlin.math.max

/*
* for the first time part 2 might be easier than first one
* but we will see
* the thought process is basically get the ranges sort merge them and count the numbers in each range
* unlikely to be duplicates or double countign since we merge all the ranges that over lap
* going to copy code from part 1
* wait i was merging differntly before i was merging the ones with the same starting point i neww to merge the ones which overlap there is a difference
*
* Lets goo we did it, will wrigte a article for each problem starting tommorrow trying to get my writing skill up you can follow that on my blog linked in the description
*
* */
fun main(){
    val inputs = readFileToString("src/main/kotlin/Day5/Day5Input.txt").split("\n")
    val ranges = mutableListOf<Pair<Long, Long>>()
    for (row in inputs) {
        if (row.contains("-")) {
            val temp = row.split("-")
            ranges.add(temp[0].toLong() to temp[1].toLong())
        }
    }
    // sort ranges by the first value then do b search on ids to find range
    // debug time lets goo ):
    ranges.sortBy { it.first }
    val newRange = mutableListOf<Pair<Long, Long>>()
    for (r in ranges){
        // this is why iam half i give up
        // intellij simplify expression proceeds to delete the whole line
        if (!newRange.isEmpty() && newRange.last().second >= r.first){
            var temp =  max(newRange.last().second, r.second)
            newRange[newRange.lastIndex] = newRange.last().first to temp
        }
        else{
            newRange.add(r)
        }
    }
    println(ranges)
    println(newRange)
    var res = 0L
    for (r in newRange){
        res += r.second+1-r.first
    }
    println(res)
}
