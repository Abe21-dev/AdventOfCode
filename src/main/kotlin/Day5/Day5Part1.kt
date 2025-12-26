package com.example.Day5

import com.example.readFileToString
import kotlin.math.max

/*
* I am not going to fix my mic so this is going to be mute video
* I will try to solve AOC day 5. No AI, No looking at solutions
* just my knogin(i think i spelt that wrong) okay lets begin
* */


/*
* thought process
* well the brute force solution is just to create an array of ranges sorted by starting point then
* read in the input and check to see if the number falls in that range
* SO : range= [(3,5) (10,14), (12,18), (16, 20)]
* input 1
* that would be O(n^2) to go through the list of ranges and check if a number is in at least one
* sort on starting number then use binary search
* we could also reduce the number of ranges we have to go through by merging ranges into bigger ones
* so the ranges above turns to range= [(3,5) (10,14), (12,18), (16, 20)]
* actually no because to much premature optimization we don't care which range its in as long as its in a range
* so we will fix this later if we have to for part 2
*
* on second thought merging is might actually be work an example for it is
* say you have the number 5 and the ranges 2-4 and 6- actually it shouldn't matter
* because to be  within a range you have to be to the right of the first number and to he left of the second number
* if you hade ranges (a,b) and (c,d) with number e and b >c and d>b and a<b<c<d mergin would make no difference for some number e which is between a and d because i would have to be in at least one of the range
* this makes more sense visually
* [a............b]
*           [c..........d]
* since 730 is not the answer lets use burt fore first to get the answer then find the optimal solution
* brute force worked with the answer being 756 time to see
*
* i think in know what is wrong with b search
* i only consider the case where the current id is to the left of first value in the range
* (nah but the other part of this is implied because if something is to the right of the first number but not in the range
* then it must be to the right of the second number meaning we would have to move our middle to the right by puttin l = mid+1
*
*
* I see the issue if you do binary search but the secon number in the pair is not properly sorted you might be meassing up you search
* lets try to think of an example
* [(1,3)(5,10)(5,8)(10,11) .....]
* say midvalue was 5,8 then i am looking for 9  then i would thinokay then the value is to the right of here ans miss out on 5,10
* The koltin sorting of collection is not too intutitive so i am going to just merge ranges  not to computational expensive just another pass over inputs
*
* lets goo merging fixed it
*
* take away don't dimiss ideas early without counter examples if you don't have a counter example to disregard the idea just keep it in you back pocket
* going to play some lofi i can't hear it but hopefully viewrs like it
* wtf that was a add
* cool right
* */

fun main() {
    val inputs = readFileToString("src/main/kotlin/Day5/Day5Input.txt").split("\n")
    val ranges = mutableListOf<Pair<Long, Long>>()
    val ids = mutableListOf<Long>()
    // that too longer than I expected
    for (row in inputs) {
        if (row.contains("-")) {
            val temp = row.split("-")
            ranges.add(temp[0].toLong() to temp[1].toLong())
        } else if (!row.isEmpty()) {
            ids.add(row.toLong())
        }
    }
    // sort ranges by the first value then do b search on ids to find range
    // debug time lets goo ):
    ranges.sortBy { it.first }
    val newRange = mutableListOf<Pair<Long, Long>>()
    for (r in ranges){
        // this is why iam half i give up
        // intellij simplify expression proceeds to delete the whole line
        if (!newRange.isEmpty() && newRange.last().first == r.first){
            var temp =  max(newRange.last().second, r.second)
            newRange[newRange.lastIndex] = newRange.last().first to temp
        }
        else{
            newRange.add(r)
        }
    }
    println(ranges.size)
    println(newRange.size)
    var res = 0
    for (id in ids) {
        if (bSearch(id, newRange)) {
            res++
        }
    }
    println(res)
}

fun bSearch(id: Long, ranges: List<Pair<Long, Long>>): Boolean {
    // trying to remember binary search
    // we need right and left pointers
    var r = ranges.size - 1// not sure if -1 is needed
    var l = 0
//    println(id)
    while (l <= r) {
        val mid = l + (r - l) / 2
        val midValue = ranges[mid]
//        println("l: $l, r: $r, mid:$mid, value:$midValue")
        if (id in midValue.first..midValue.second) {
            return true
        } else if (id < midValue.first) {
            r = mid - 1
        } else {
            l = mid + 1
        }
    }
    // kinda lost how do, it might be okay to look up b search algo jut to be sure
    return false
}
// thats weird



