package advent.of.code.year2021.day5

import advent.of.code.year2021.ContentReader

class Day5 {
    fun task1(lines: List<String>): Int {
        return countInterceptorsBetweenLines(lines) { l -> LineCreator.createHorizontalOrVerticalLines(l, false) }
    }

    fun task2(lines: List<String>): Int {
        return countInterceptorsBetweenLines(lines) { l -> LineCreator.createHorizontalOrVerticalLines(l, true) }
    }


    private fun countInterceptorsBetweenLines(
        lines: List<String>,
        linesFactorFunction: (s: String) -> Set<Point>
    ): Int {
        return lines
            .flatMap { linesFactorFunction(it) }
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
    println(day5.task1(data))
    println(day5.task2(data))
}