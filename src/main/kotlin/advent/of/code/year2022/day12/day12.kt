package advent.of.code.year2022.day12

import advent.of.code.year2022.ContentReader2022
import advent.of.code.year2022.day9.Position

import kotlin.system.measureTimeMillis

const val day = 12


fun main() {

    val timeInMillis = measureTimeMillis { solve() }

    println("Task 12 finished in $timeInMillis ms")
}

fun solve() {
    val flows = ContentReader2022.readFileAsLines(day)
        .let { RiverPositonGenerator.generateRiverPosition(it) }
    val resultPart1 = solvePart1(flows)
    println(resultPart1)

    val resultPart2 = solvePart2(flows)
    println(resultPart2)
}

fun solvePart1(signals: Map<Position, Char>): Int {
    return signals.findStartPoints('S').minOf { PathBuilder(signals, it).buildPath() }
}

fun solvePart2(signals: Map<Position, Char>): Int {
    return signals.findStartPoints('a').minOf { PathBuilder(signals, it).buildPath() }
}

fun Map<Position, Char>.findStartPoints(char: Char): List<Position> {
    return this.entries.filter { it.value == char }.map { it.key }
}