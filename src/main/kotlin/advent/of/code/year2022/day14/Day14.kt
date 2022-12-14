package advent.of.code.year2022.day14

import advent.of.code.year2022.ContentReader2022

import kotlin.system.measureTimeMillis

const val day = 14

fun main() {

    val timeInMillis = measureTimeMillis { solve() }

    println("Task 14 finished in $timeInMillis ms")
}


fun solve() {
    val stones = ContentReader2022.readFileAsLines(14)
        .let { BeachGenerator.generateBeach(it) }

    println(solve1(stones))
    println(solve2(stones))
}

fun solve1(stones: Map<BeachPoint, BeachObject>): Int {
    val beach = Beach(stones.toMutableMap())
    beach.playWithSand { beach.isGoingToAbyss(it) }
    return beach.countSand()
}


fun solve2(stones: Map<BeachPoint, BeachObject>): Int {
    val beach = Beach(stones.toMutableMap(),true)
    beach.playWithSand { it == beach.getSandStartingPoint() }
    return beach.countSand()
}
