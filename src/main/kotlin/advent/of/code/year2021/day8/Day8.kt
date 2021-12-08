package advent.of.code.year2021.day8

import advent.of.code.year2021.ContentReader

class Day8 {
    private val sevenSegmentDisplay = mapOf(
        0 to 6,
        1 to 2,
        2 to 5,
        3 to 5,
        4 to 4,
        5 to 5,
        6 to 6,
        7 to 3,
        8 to 7,
        9 to 6
    )

    fun part1(lines: List<String>): Int {
        val easyDigitsValues = setOf(
            sevenSegmentDisplay[1], sevenSegmentDisplay[4], sevenSegmentDisplay[7], sevenSegmentDisplay[8]
        )
        return lines
            .filter { it.isNotEmpty() }
            .map { it.split(" | ")[1] }
            .flatMap { it.split(" ") }
            .filter { easyDigitsValues.contains(it.toSet().size) }
            .size
    }
}

fun main() {
    val data = ContentReader.readFileAsLines(8)
    val day8 = Day8()
    println(day8.part1(data))
}
