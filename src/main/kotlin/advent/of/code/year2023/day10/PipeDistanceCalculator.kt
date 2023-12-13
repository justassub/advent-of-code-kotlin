package advent.of.code.year2023.day10

import advent.of.code.util.Direction
import advent.of.code.util.Point
import advent.of.code.util.recreateByDirectionReverse

class PipeFinishedException : RuntimeException()
class PipeDistanceCalculator(
    private val pipes: Map<Point, Char>,
    private val startConnectingPosition: Pair<Point, Char>
) {
    private val start = pipes.filter { it.value == 'S' }.firstNotNullOf { it.key }
    private val cache: MutableMap<Point, Int> = mutableMapOf(start to 0)

    fun calculateHighestDistance(): Int {
        var currentPoint = startConnectingPosition
        var currentPathCount = 1

        while (true) {
            cache[currentPoint.first] = currentPathCount

            val nextPoint = try {
                currentPoint.getNextLocation()
            } catch (e: PipeFinishedException) {
                return (currentPathCount / 2) + 1
            }

            currentPoint = nextPoint to pipes[nextPoint]!!
            currentPathCount++
        }
    }

    private fun Pair<Point, Char>.getNextLocation(): Point {

        val nextPipes = this.second.getDirections()
            .map { this.first.recreateByDirectionReverse(it) }
            .filterNot { cache.containsKey(it) }


        if (nextPipes.size > 1) {
            throw IllegalArgumentException("Should not happen $nextPipes")
        }
        if (nextPipes.isEmpty()) {
            throw PipeFinishedException()
        }
        return nextPipes.first()
    }

    private fun Char.getDirections(): List<Direction> {
        return when (this) {
            '|' -> listOf(Direction.UP, Direction.DOWN)
            '-' -> listOf(Direction.RIGHT, Direction.LEFT)
            'L' -> listOf(Direction.UP, Direction.RIGHT)
            'J' -> listOf(Direction.UP, Direction.LEFT)
            '7' -> listOf(Direction.DOWN, Direction.LEFT)
            'F' -> listOf(Direction.DOWN, Direction.RIGHT)
            else -> throw IllegalArgumentException(" Should not happen.. Char : $this")
        }
    }
}