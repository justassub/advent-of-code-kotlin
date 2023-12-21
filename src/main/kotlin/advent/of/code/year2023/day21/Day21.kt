package advent.of.code.year2023.day21

import advent.of.code.util.PointBuilder
import advent.of.code.util.printResult

fun main() {
    val data = PointBuilder.createPointsFromFile(2023, 21)

    Garden(data)
        .walkSteps(64)
        .count()
        .printResult()


}