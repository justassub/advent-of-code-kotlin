package advent.of.code.year2021.day8

import advent.of.code.year2021.ContentReader

class Day8 {


    fun part1(lines: List<String>): Int {
        return createDigitsAndOutput(lines).sumOf { it.calculateEasyValuesCount() }
    }

    fun part2(lines: List<String>): Int {
        val signals = createDigitsAndOutput(lines)
        return signals.sumOf { it.calculateOutputSum() }
    }

    private fun createDigitsAndOutput(lines: List<String>): List<Signal> {
        return lines
            .filter { it.isNotEmpty() }
            .map { it.toSignal() }
    }
}

fun main() {
    val data = ContentReader.readFileAsLines(8)
    val day8 = Day8()
    println(day8.part1(data))
    println(day8.part2(data))
}

private fun String.toSignal(): Signal = this.split(" | ")
    .let { (digits, numbers) ->
        Signal(
            digits.split(" ").map { it.sortString() },
            numbers.split(" ").map { it.sortString() })
    }

private fun String.sortString(): String = this.toSortedSet().joinToString("")
