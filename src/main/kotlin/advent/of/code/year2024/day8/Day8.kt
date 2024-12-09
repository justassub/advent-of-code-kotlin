package advent.of.code.year2024.day8

import advent.of.code.util.*

fun main() {
    val points = PointBuilder.createPointsFromFile(2024, 8)


    val maxX = points.findMaxX()
    val maxY = points.findMaxY()

    val minX = points.findMinX()
    val minY = points.findMinY()

    fun belongToMap(p: Point) = p.x in minX..maxX && p.y in minY..maxY

    points
        .filter { it.value != '.' }
        .entries.groupBy({ it.value }, { it.key })
        .values
        .asSequence()
        .flatMap { it.createPossibleCombinationsWithoutSelf() }
        .flatMap { createPointsMirrorView(it.first, it.second) }
        .filter { belongToMap(it) }
        .distinct()
        .count()
        .printResult()


}