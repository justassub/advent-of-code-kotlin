package advent.of.code.year2022.day10

import advent.of.code.year2022.ContentReader2022
import kotlin.system.measureTimeMillis

const val day = 10


fun main() {

    val timeInMillis = measureTimeMillis { solve() }

    println("Task 10 finished in $timeInMillis ms")
}

fun solve() {
    val signals = ContentReader2022.readFileAsLines(day)
        .map { SignalGenerator.generateSignal(it) }
    val resultPart1 = solvePart1(signals)

    println(resultPart1)

}

fun solvePart1(signals: List<SignalAction>): Int {
    val calculator = signals
        .let { SignalActionAssociator.associateSignalActionsWithCycles(it) }
        .let { SignalScoreCalculator(it) }
    return listOf(20, 60, 100, 140, 180, 220)
        .sumOf { it * calculator.calculateScoreOfCycles(it).last().second }
}


