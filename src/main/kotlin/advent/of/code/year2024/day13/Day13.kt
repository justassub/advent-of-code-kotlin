package advent.of.code.year2024.day13

import advent.of.code.ContentReader
import advent.of.code.util.equation.Equation
import advent.of.code.util.equation.Equation2D
import advent.of.code.util.equation.canBeSolved
import advent.of.code.util.printResult

fun main() {
    val data = ContentReader.readAsGroupOfLinesSplitByEmptyLine(2024, 13)
    //solveExercise(data, 0)
    solveExercise(data, 10000000000000)
}

private fun solveExercise(data: List<List<String>>, changePrizeLocationBy: Long) {
    val solvableClaws = data
        .map { createEquation(it, changePrizeLocationBy) }
        .map { it.solve() }
        .filter { it.canBeSolved() }

    solvableClaws
        .sumOf { 3 * it.first + it.second }
        .toLong()
        .printResult()
}


fun createEquation(data: List<String>, changePrizeLocationBy: Long): Equation2D {
    val (firstEquation, secondEquation, prizeEquation) = data
    val (xA, yA) = firstEquation.split("Button A:")[1]
        .split(",")
        .map { it.split("+")[1].toLong() }
    val (xB, yB) = secondEquation.split("Button B:")[1]
        .split(",")
        .map { it.split("+")[1].toLong() }

    val (xC, yC) = prizeEquation.split("Prize:")[1]
        .split(",")
        .map { it.split("=")[1].toLong() }

    return Equation2D(Equation(xA, xB, xC + changePrizeLocationBy), Equation(yA, yB, yC + changePrizeLocationBy))
}