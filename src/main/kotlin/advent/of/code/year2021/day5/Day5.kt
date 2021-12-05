package advent.of.code.year2021.day5

import advent.of.code.year2021.ContentReader

class Day5 {
    fun task1(lines: List<String>): Int {
        return lines
            .flatMap { LineCreator.createHorizontalOrVerticalLines(it) }
            .groupingBy { it }
            .eachCount()
            .filter { it.value > 1 }
            .map { it.value }
            .count()
    }

}

fun main() {
    val data = ContentReader.readFileAsLines(5)
    val day5 = Day5()
    println(day5.task1(data) )
}