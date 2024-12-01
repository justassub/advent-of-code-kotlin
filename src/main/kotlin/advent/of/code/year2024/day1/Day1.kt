package advent.of.code.year2024.day1

import advent.of.code.ContentReader
import kotlin.math.abs

val data = ContentReader.readFileAsLines(2024, 1)
    .map { it.split("   ").map { n -> n.toInt() } }
    .map { it[0] to it[1] }

fun main() {
    println(solvePart1(data))
    println(solvePart2(data))
}

fun solvePart1(data: List<Pair<Int, Int>>): Int {
    val leftNumbersSorter = data.map { it.first }.sorted()
    val rightNumbersSorter = data.map { it.second }.sorted()

    return leftNumbersSorter.zip(rightNumbersSorter)
        .sumOf { abs(it.second - it.first) }
}

fun solvePart2(data: List<Pair<Int, Int>>): Int {
    val leftNumbers = data.map { it.first }
    val secondNumbersWithCount = data.map { it.second }
        .groupingBy { it }
        .eachCount()

    return leftNumbers
        .sumOf { it * secondNumbersWithCount.getOrDefault(it, 0) }
}