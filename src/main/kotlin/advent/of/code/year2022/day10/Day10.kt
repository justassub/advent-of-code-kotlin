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
    solvePart2(signals)
}

fun solvePart1(signals: List<SignalAction>): Int {
    val calculator = signals
        .let { SignalActionAssociator.associateSignalActionsWithCycles(it) }
        .let { SignalScoreCalculator(it) }
    return listOf(20, 60, 100, 140, 180, 220)
        .sumOf { it * calculator.calculateScoreOfCycles(it).last().second }
}

fun solvePart2(signals: List<SignalAction>): Unit {


    var currentCycle = 1
    var currentValue = 1
    val cyclesWithValues = mutableMapOf<Int, Int>()
    cyclesWithValues[0] = currentValue
    cyclesWithValues[currentCycle] = currentValue

    for (it in signals) {
        if (it.signalType == SignalType.NOOP) {
            currentCycle++
            cyclesWithValues[currentCycle] = currentValue
        } else {
            currentCycle++
            cyclesWithValues[currentCycle] = currentValue
            currentCycle++
            currentValue += it.value
            cyclesWithValues[currentCycle] = currentValue
        }
    }


    fun printCRT(from: Int) {
        (1..40)
            .map { it in (cyclesWithValues.getValue(it + from)..cyclesWithValues.getValue(it + from) + 2) }
            .map { if (it) '#' else '.' }
            .joinToString("")
            .let { println(it) }

    }

    (0..240 step 40)
        .forEach { printCRT(it) }
}
