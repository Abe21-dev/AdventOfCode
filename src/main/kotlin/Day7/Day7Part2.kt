package com.example.Day7

import com.example.readFileToString
import java.util.LinkedList


/**
 * Thought process
 * Since a timeline is just a unique path we need to count the number of unique path that form the start
 * if you look at it and think well there are 21 split for the sample imput that means 21 L or R decisions  so it must
 * be 2^21 but that is wrong because puzzle input says that there are 42 unique paths in the sample input.
 * before we get to a solution lets list what we know
 *
 * One possible way is to construct a trie representing all the paths then just count the number of leaf nodes and that is number of
 * possible timeline paths
 *
 * stuck
 * what do we know
 * - we can explore the paths of the beam via bfs
 *
 * what do we want
 * - count the number of unique paths the beam took
 *
 * assumptions
 * - if you consider the paths of the beam as nodes on a trie then the number of unique paths is equal
 * to the number of leaf nodes. we should probably verfiy this is a property of a trie(this is correct cause the leaf nodes of a trie represent unique words
 * therefor the paths they follow are unique if ther werent then the two leaf nodes would have the same word)
 * -its okay if you count ^ as part of the. we need to verify that there is no counter example
 * where considering the ^ as path results in a wrong count for the number of unique conts
 *
 * problems we are facing
 * - we are under counting and we need to figure out why
 *
 *
 */

val memo = mutableMapOf<Pair<Int, Int>, Long>()

fun main() {
    val input = readFileToString("src/main/kotlin/Day7/Day7Input.txt").split("\n").map { it.split("").toMutableList() }
    val q = LinkedList<TrieNode>()
    // find the first value
    val counter = mutableListOf(0)
    val root = 1 to input[0].indexOf("S")
   println( dfsTrie(root, input))

}

fun dfsTrie(idx: Pair<Int, Int>, input: List<MutableList<String>>) : Long {
    if(!inBounds(idx, input)){
        return 1
    }
    if (idx in memo){
        if(memo[idx] == null){
            println("Panic")
        }
        return  memo[idx] ?: 0
    }
    val curr = input[idx.first][idx.second]

    var result = 0L
    if (curr == "^"){
        val leftIdx = idx.first+1 to idx.second - 1
        val rightIdx = idx.first+1 to idx.second + 1
        result =  dfsTrie(leftIdx, input)+dfsTrie(rightIdx, input)
    }
    else if (curr =="."){
        val newIdx = idx.first + 1 to idx.second
        result  = dfsTrie(newIdx, input)
    }
    memo[idx] = result
    return  result
}

fun <T> inBounds(idx: Pair<Int, Int>, input: List<List<T>>): Boolean {
    return idx.first in 0..<input.size && idx.second in 0..<input[0].size
}

class TrieNode(val value: String, val idx: Pair<Int, Int>) {
    var next: MutableList<TrieNode> = mutableListOf()
    override fun toString(): String {
        return "idx: $idx, next: ${next.size}"
    }
}
