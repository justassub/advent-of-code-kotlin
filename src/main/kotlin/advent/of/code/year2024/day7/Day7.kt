package advent.of.code.year2024.day7

import advent.of.code.ContentReader
import advent.of.code.util.builder.ListBuilder
import advent.of.code.util.math.MathUtil
import advent.of.code.util.printResult


fun main() {
    val lines = ContentReader.readFileAsLines(2024, 7)
        .map { it.split(": ") }
        .map { it[0] to it[1] }


    solveDay(lines, listOf("+", "*"))
}

private fun solveDay(lines: List<Pair<String, String>>, possibleVariants: List<String>) {
    val possibleCombinations = lines
        .map {
            it.first.toLong() to
                    ListBuilder.buildPossibleCombinationsFromString(
                        it.second,
                        " ",
                        possibleVariants
                    ).map { it.replace("|", " ") }
        }


    possibleCombinations
        .map { it.first to it.second.map { expression -> MathUtil.solveEvaluationWithoutParenthesesNoPriority(expression) } }
        .filter { it.second.contains(it.first) }
        .sumOf { it.first }
        .printResult()
}