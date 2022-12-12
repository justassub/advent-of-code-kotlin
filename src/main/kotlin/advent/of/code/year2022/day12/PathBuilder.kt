package advent.of.code.year2022.day12

import advent.of.code.year2022.day9.Position
import advent.of.code.year2022.day9.plus
import java.util.*


class PathBuilder(
    private val data: Map<Position, Char>,
    private val start: Position
) {
    private val goal = data.findGoal()

    private var shortestPathSoFar: Int = Int.MAX_VALUE;
    fun buildPath(): Int {
        buildQueue(LinkedList(listOf(start to 0)), mutableSetOf())
        return shortestPathSoFar
    }


    private fun buildQueue(
        queue: Queue<Pair<Position, Int>>,
        visited: MutableSet<Position>
    ) {
        while (!queue.isEmpty()) {
            val (currentPosition, distance) = queue.poll()
            val currentValue = data[currentPosition]!!
            if (goal == currentPosition) {
                shortestPathSoFar = distance.coerceAtMost(shortestPathSoFar)
                return
            }
            visited.add(currentPosition)
            currentPosition.findOtherDestinations()
                .filter { data.containsKey(it) }
                .filter { !visited.contains(it) }
                .filter { currentValue.isUpperCase() || data.getValue(it) - 1 <= currentValue }
                .forEach {
                    queue.add(it to distance + 1)
                    visited.add(it)
                }
        }
    }

}

private fun Position.findOtherDestinations(): Set<Position> {
    return setOf(
        Position(1, 0) + this,
        Position(-1, 0) + this,
        Position(0, 1) + this,
        Position(0, -1) + this,
    )
}

fun Map<Position, Char>.findGoal(): Position {
    val endPosition = this.entries.first { it.value == 'E' }.key
    return endPosition.findOtherDestinations().maxBy { this.getValue(it) }
}

