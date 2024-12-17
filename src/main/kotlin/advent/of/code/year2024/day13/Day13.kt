package advent.of.code.year2024.day13

import advent.of.code.ContentReader
import advent.of.code.util.equation.Equation
import advent.of.code.util.equation.Equation2D
import advent.of.code.util.printResult

fun main() {
    val claws = ContentReader.readAsGroupOfLinesSplitByEmptyLine(2024, 13)
        .map(::createEquation)
        .map { it.solve() }
        .filter(::canBeSolved)

    claws
        .sumOf { 3 * it.first + it.second }
        .toLong()
        .printResult()
}

fun canBeSolved(pair: Pair<Double, Double>): Boolean {
    val (n1, n2) = pair
    return n1.toInt().toDouble() == n1 && n2.toInt().toDouble() == n2
}

fun createEquation(data: List<String>): Equation2D {
    val (firstEquation, secondEquation, prizeEquation) = data
    val (xA, yA) = firstEquation.split("Button A:")[1]
        .split(",")
        .map { it.split("+")[1].toInt() }
    val (xB, yB) = secondEquation.split("Button B:")[1]
        .split(",")
        .map { it.split("+")[1].toInt() }

    val (xC, yC) = prizeEquation.split("Prize:")[1]
        .split(",")
        .map { it.split("=")[1].toInt() }

    return Equation2D(Equation(xA, xB, xC), Equation(yA, yB, yC))
}