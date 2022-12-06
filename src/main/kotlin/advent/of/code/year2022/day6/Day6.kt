package advent.of.code.year2022.day6

import advent.of.code.year2022.ContentReader2022
import kotlin.system.measureTimeMillis

const val day = 6


fun main() {
    val timeInMillis = measureTimeMillis { solve() }

    println("Task 6 finished in $timeInMillis ms")
}

fun solve() {
    val text = ContentReader2022.readFileAsText(day)
    val signal = ComSignal(text)
    val p1Result = signal.findNthUniqueAppearance(4)
    val p2Result = signal.findNthUniqueAppearance(14)

    println(p1Result)
    println(p2Result)
}
