package advent.of.code.year2023.day11

import advent.of.code.util.Point
import advent.of.code.util.PointBuilder.createPointsFromFile
import advent.of.code.util.createPossibleCombinationsWithoutSelf
import advent.of.code.year2021.LargePoint
import kotlin.math.absoluteValue


fun main() {
    val points = createPointsFromFile(2023, 11)

    val expandedGalaxies = expandGalaxy(points)

    calculateDistanceSum(expandedGalaxies)
}

private fun calculateDistanceSum(expandedGalaxies: List<LargePoint>) {
    expandedGalaxies.createPossibleCombinationsWithoutSelf()
        .sumOf { (it.first.x - it.second.x).absoluteValue + (it.first.y - it.second.y).absoluteValue }
        .run { println(this) }
}


fun expandGalaxy(points: Map<Point, Char>): List<LargePoint> {
    val galaxies = points.filter { it.value == '#' }.map { it.key }
    val galaxiesX = galaxies.map { it.x }.distinct()
    val galaxiesY = galaxies.map { it.y }.distinct()
    val minX = points.minOf { it.key.x }
    val maxX = points.maxOf { it.key.x }
    val expandTheseX = (minX..maxX)
        .filterNot { galaxiesX.contains(it) }
    val minY = points.minOf { it.key.y }
    val maxY = points.maxOf { it.key.y }

    val expandTheseY = (minY..maxY)
        .filterNot { galaxiesY.contains(it) }

    return galaxies
        .map { g ->
            LargePoint(
                x = g.x.toLong() +   expandTheseX.count { it < g.x },
                y = g.y.toLong() +   expandTheseY.count { it < g.y }
            )
        }
}

