package advent.of.code.year2022.day16

import advent.of.code.year2022.ContentReader2022

import kotlin.system.measureTimeMillis

const val day = 16
fun main() {

    val timeInMillis = measureTimeMillis { solve() }

    println("Task 16 finished in $timeInMillis ms")
}

fun solve() {
    val valves = ContentReader2022.readFileAsLines(day)
        .let { ValveGenerator.generateValves(it) }
    println(solvePart1(valves))

}

fun solvePart1(valves: Map<String, Valve>):Int{
    return ShortestPathFinder(valves).funFindMaxFlowes("AA")
}
