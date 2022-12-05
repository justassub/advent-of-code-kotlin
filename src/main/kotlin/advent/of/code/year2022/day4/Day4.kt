package advent.of.code.year2022.day4

import advent.of.code.year2022.ContentReader2022
import kotlin.system.measureTimeMillis

const val day = 4


fun main() {
    val timeInMillis = measureTimeMillis { solve() }

    println("Task 4 finished in $timeInMillis ms")
}

fun solve() {
    val pairs = ContentReader2022.readFileAsLines(day)
        .map { SanitizerMaker.createPairSanitizers(it) }
    solvePart1(pairs)
    solvePart2(pairs)
}


fun solvePart1(pairs: List<Pair<SanitizerElf, SanitizerElf>>) {
    pairs
        .count { it.overlapFully() }
        .let { println(it) }

}

fun solvePart2(pairs: List<Pair<SanitizerElf, SanitizerElf>>) {
    pairs
        .count { it.overlap() }
        .let { println(it) }

}