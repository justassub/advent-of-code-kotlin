package advent.of.code.year2021.day7

import advent.of.code.year2021.ContentReader
import kotlin.math.absoluteValue

class Day7 {

    fun part1(numbers: List<Int>): Long {
        return calculateFuels(numbers) { index, distance -> (distance - index).absoluteValue.toLong() }
            .minOrNull()!!
    }

    fun part2(numbers: List<Int>): Long {
        return calculateFuels(numbers) { index, distance ->
            gaussianSummation(
                (distance - index).absoluteValue
            )
        }.minOrNull()!!
    }

    private fun calculateFuels(numbers: List<Int>, sumOff: (index: Int, distance: Int) -> Long): List<Long> {
        val min = numbers.minOrNull() ?: 0
        val max = numbers.maxOrNull() ?: 0
        return (min..max)
            .map { index -> numbers.sumOf { sumOff(index, it) } }
    }

    private fun gaussianSummation(stepsDifference: Int): Long {
        return (stepsDifference * (stepsDifference.absoluteValue + 1)).toLong() / 2
    }
}

fun main() {
    val numbers = ContentReader.readFileAsLines(7)[0].split(",")
        .map { it.toInt() }
    val day7 = Day7()
    println(day7.part1(numbers))
    println(day7.part2(numbers))


}
