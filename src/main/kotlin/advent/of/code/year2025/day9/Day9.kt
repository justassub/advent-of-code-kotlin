package advent.of.code.year2025.day9

import advent.of.code.util.PointBuilder.createPointsWithValuesFromLinesText
import advent.of.code.util.createPossibleCombinationsWithoutSelf
import advent.of.code.util.printResult
import kotlin.math.absoluteValue

fun main() {

    val points = createPointsWithValuesFromLinesText(2025, 9, ",")

    points
        .createPossibleCombinationsWithoutSelf()
        .maxOf { (p1, p2) ->
            (p1.x - p2.x).absoluteValue.toLong().inc() *
                    (p1.y - p2.y).absoluteValue.toLong().inc()
        }
        .printResult()
}