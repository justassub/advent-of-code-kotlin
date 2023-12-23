package advent.of.code.year2023.day23

import advent.of.code.util.*

fun main() {
    val points = PointBuilder.createPointsFromFile(2023, 23)
    val (slopes, paths) = part1(points)
    val elements = slopes + paths

    ForestHiking(elements)
        .findLongestPath()
        .printResult()

}

private fun part1(points: Map<Point, Char>): Pair<Map<Point, Slope>, Map<Point, Path>> {
    val forests = points
        .filter { it.value == '#' }

    val slopes = points
        .filter { it.value.isDirection() }
        .mapValues { Slope(it.value.createDirection()) }

    val paths = points
        .filter { it.value == '.' }
        .mapValues {
            Path(it.key.findNeighbours()
                .filter { p -> points.containsKey(p) }
                .filterNot { p -> forests.containsKey(p) }.toSet()
            )
        }
    return Pair(slopes, paths)
}


sealed class HikingElement

data class Path(val possiblePaths: Set<Point>) : HikingElement()
data class Slope(val direction: Direction) : HikingElement()