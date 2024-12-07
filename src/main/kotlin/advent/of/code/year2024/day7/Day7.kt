package advent.of.code.year2024.day7

import advent.of.code.ContentReader
import advent.of.code.util.builder.ListBuilder
import advent.of.code.util.math.MathUtil
import advent.of.code.util.printResult


fun main() {
    val lines = ContentReader.readFileAsLines(2024, 7)
        .map { it.split(": ") }
        .map { it[0].toLong() to it[1] }


    val possibleLines1 = solveDay(lines, listOf("+", "*"))
        .map { it.first }
        .toSet()
    val part1Answer = possibleLines1
        .sum()

    part1Answer.printResult()

    (part1Answer + solveDay(
        lines.filter { possibleLines1.contains(it.first).not() },
        listOf("+", "*", "concatenation")
    ).sumOf { it.first }
            ).printResult()


}

private fun solveDay(lines: List<Pair<Long, String>>, possibleVariants: List<String>): List<Pair<Long, List<Long>>> {
    val possibleCombinations = lines
        .map {
            it.first to
                    ListBuilder.buildPossibleCombinationsFromString(
                        it.second,
                        " ",
                        possibleVariants
                    ).map { it.replace("|", " ") }
        }


    return possibleCombinations
        .map { it.first to it.second.map { expression -> MathUtil.solveEvaluationWithoutParenthesesNoPriority(expression) } }
        .filter { it.second.contains(it.first) }
}