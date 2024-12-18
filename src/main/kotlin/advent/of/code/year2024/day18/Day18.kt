package advent.of.code.year2024.day18

import advent.of.code.util.Direction
import advent.of.code.util.Point
import advent.of.code.util.PointBuilder
import advent.of.code.util.recreateByDirectionReverse
import kotlin.math.min

val minimumX = 0
val minimumY = 0
val maximumX = 70
val maximumY = 70

val allowBlocks = 1024

fun pointInBounds(point: Point): Boolean {
    return point.x in minimumX..maximumX && point.y in minimumY..maximumY
}

fun main() {
    val corruptedPoints = PointBuilder.createPointsWithValuesFromLinesText(2024, 18, ",")
        .take(allowBlocks)
        .toSet()

    val player = Point(minimumX, minimumY)
    val goal = Point(maximumX, maximumY)
    var minScore = Integer.MAX_VALUE
    findPathSimple(
        player,
        goal,
        corruptedPoints
    ) {
        minScore = min(minScore, it.score)
    }
    println(minScore)
}


fun findPathSimple(
    start: Point,
    goal: Point,
    blocks: Set<Point>,
    onGoal: (PointPathFinder) -> Unit
) {
    val queue = mutableListOf(PointPathFinder(start, 0, setOf(start)))
    val visited = mutableMapOf<Point, Int>()
    while (queue.isNotEmpty()) {
        val current = queue.removeFirst()
        if (visited.contains(current.point) && current.score >= visited[current.point]!!) {
            continue
        }
        if (current.point == goal) {
            onGoal(current)
            continue
        }
        visited[current.point] = current.score
        val possibleDestinations = Direction.entries
            .map { current.point.recreateByDirectionReverse(it) }
            .filter { pointInBounds(it) }
            .filter { it !in blocks }
            .map { PointPathFinder(it, current.score + 1, current.path + it) }
        possibleDestinations
            .forEach(queue::add)
    }

}

data class PointPathFinder(val point: Point, val score: Int, val path: Set<Point>)

