package advent.of.code.year2025.day6

import advent.of.code.ContentReader.readFileAsLines
import advent.of.code.util.math.Operator
import advent.of.code.util.math.applyOperator
import advent.of.code.util.printResult


fun main() {
    val data = readFileAsLines(2025, 6)
    val mathOperators = data.last()
        .split(Regex("\\s+"))
        .map { Operator.fromString(it) }

    val numbers = data.dropLast(1)
        .map { it.trim() }
        .map { it.split(Regex("\\s+")) }
        .map { it.map { n -> n.toLong() } }


    mathOperators
        .mapIndexed { i, operator ->
            numbers.map { it[i] }.applyOperator(operator)
        }
        .sum()
        .printResult()


}
