package advent.of.code.year2023.day23

import advent.of.code.util.Point
import advent.of.code.util.recreateByDirectionReverse
import java.util.*
import kotlin.math.max

data class ForestHiking(val elements: Map<Point, HikingElement>) {

    private val start = Point(1, 0)
    private val goal = elements.maxBy { it.key.y }.key


    fun findLongestPath(): Int {
        val visited = mutableSetOf(start)

        val startingPosition = start to visited

        val queue: LinkedList<Pair<Point, MutableSet<Point>>> = LinkedList()
        queue.add(startingPosition)
        var longestPath = 0
        while (queue.isNotEmpty()) {
            val (currentPosition, currentHistory) = queue.poll()
            if (currentPosition == goal) {
                longestPath = max(longestPath, currentHistory.size - 1)
                continue
            }

            val nextPaths = when (val currentValue = elements[currentPosition]!!) {
                is Slope -> listOf(currentPosition.recreateByDirectionReverse(currentValue.direction))
                is Path -> currentValue.possiblePaths
            }.filterNot { currentHistory.contains(it) }

            val nextPathsWithHistory: List<Pair<Point, MutableSet<Point>>> = when (nextPaths.size) {
                0 -> emptyList()
                1 -> listOf(nextPaths.first() to currentHistory)
                else -> listOf(nextPaths.first() to currentHistory) + nextPaths.drop(1)
                    .map { it to currentHistory.toMutableSet() }
            }
                .onEach { it.second.add(it.first) }


//            val nextPaths = when (val currentValue = elements[currentPosition]!!) {
//                is Slope -> listOf(currentPosition.recreateByDirectionReverse(currentValue.direction) to currentHistory)
//                is Path -> currentValue.possiblePaths.map { createElementsForMaps(it, currentHistory) }
//            }
//                .filterNot { currentHistory.contains(it.first) }

            queue.addAll(nextPathsWithHistory)
        }
        return longestPath
    }

    private fun createElementsForMaps(
        it: Point,
        currentHistory: MutableSet<Point>
    ) {

        it to currentHistory.toMutableSet()
    }

}