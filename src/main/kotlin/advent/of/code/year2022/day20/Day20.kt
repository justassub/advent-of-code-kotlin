package advent.of.code.year2022.day20

import advent.of.code.year2022.ContentReader2022
import kotlin.system.measureTimeMillis

const val day = 20
fun main() {
    val timeInMillis = measureTimeMillis { solve() }

    println("Task 20 finished in $timeInMillis ms")
}


fun solve() {
    val points = ContentReader2022.readFileAsLines(day)
        .map { it.toInt() }


    println(solvePart1(points))
}

fun solvePart1(points: List<Int>): Int {
    return NumberMixer.mixNumbers(points)
        .let {
            val finder = NumberNThFinder(it)
            finder.findRepeatNthNumber(1000) + finder.findRepeatNthNumber(2000) + finder.findRepeatNthNumber(3000)
        }
}
