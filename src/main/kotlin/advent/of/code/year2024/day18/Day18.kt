package advent.of.code.year2024.day18

import advent.of.code.util.*
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
    val corruptedPointsAll = PointBuilder.createPointsWithValuesFromLinesText(2024, 18, ",")

    val stock = corruptedPointsAll
        .drop(allowBlocks)

    val corruptedPoints = corruptedPointsAll
        .take(allowBlocks)
        .toMutableSet()
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

    stock
        .first {
            var validPath = false
            corruptedPoints.add(it)
            findPathSimple(
                player,
                goal,
                corruptedPoints
            ) {
                validPath = true
            }
            !validPath
        }.printResult()

}


fun findPathSimple(
    start: Point,
    goal: Point,
    blocks: Set<Point>,
    onGoal: (PointPathFinder) -> Unit
) {
    val queue = mutableListOf(PointPathFinder(start, 0))
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
            .map { PointPathFinder(it, current.score + 1) }
        possibleDestinations
            .forEach(queue::add)
    }

}

data class PointPathFinder(val point: Point, val score: Int)

