package advent.of.code.year2024.day12

import advent.of.code.util.PointBuilder
import advent.of.code.util.TraversablePoint
import advent.of.code.util.printResult
import advent.of.code.util.shape.Shape2D

fun main() {
    val points = PointBuilder.createPointsFromFile(2024, 12)
        .let {
            PointBuilder.traversablePointsFromMapSimpleDirections(
                it,
                { c -> c },
                { a, b -> a.value == b.value }
            )
        }
    createShapes(points)
        .sumOf { it.points.size * it.calculatePerimeter() }
        .printResult()

}

fun <T> createShapes(points: Set<TraversablePoint<T>>): Set<Shape2D<T>> {

    return points
        .map {
            val l = mutableSetOf(it)
            it.collectAllJoiningPoints(l)
            l
        }
        .distinct()
        .map { Shape2D(it) }
        .toSet()
}