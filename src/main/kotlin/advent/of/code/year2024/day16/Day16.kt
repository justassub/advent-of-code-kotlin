package advent.of.code.year2024.day16

import advent.of.code.util.*
import java.util.*
import kotlin.math.min

fun main() {
    val map = PointBuilder.traversablePointsFromMapSimpleDirections(
        PointBuilder.createPointsFromFile(2024, 16)
    ) { c -> c }


    val start = map.first { it.value == 'S' }
    val goal = map.first { it.value == 'E' }

    val direction = Direction.RIGHT

    val visited = mutableMapOf((start to direction) to 0L)

    findShortestPath(
        visited,
        goal,
        LinkedList(listOf(MoveStep(start, direction, 0)))
    ).printResult()
    println()
}

fun findShortestPath(
    visited: MutableMap<Pair<TraversablePoint<Char>, Direction>, Long>,
    goal: TraversablePoint<Char>,
    queue: LinkedList<MoveStep>,
): Long {
    var minimum = Long.MAX_VALUE

    while (queue.isNotEmpty()) {
        val current = queue.poll()
        if (current.point == goal) {
            minimum = min(minimum, current.points)
        }
        val direction = current.direction
        val nextPoint = current.point.findNextNeighbourByDirection(direction) { it.value != '#' }
            ?.let { MoveStep(it, direction, current.points + 1) }

        val rotatePositions = direction.rotate90DegreesBothSide()
            .map { current.point.findNextNeighbourByDirection(it) { n -> n.value != '#' } to it }
            .filter { it.first != null }
            .map { MoveStep(it.first!!, it.second, current.points + 1001) }

        (rotatePositions + nextPoint)
            .filterNotNull()
            .filter { it.points < visited.getOrDefault(it.point to it.direction, Long.MAX_VALUE) }
            .forEach {
                visited[it.point to it.direction] = it.points
                queue.add(it)
            }
    }
    return minimum
}

data class MoveStep(
    val point: TraversablePoint<Char>,
    val direction: Direction,
    val points: Long,
    val visitedPaths: MutableSet<Point> = mutableSetOf()
)