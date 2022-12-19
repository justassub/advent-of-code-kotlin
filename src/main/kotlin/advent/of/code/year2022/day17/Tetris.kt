package advent.of.code.year2022.day17

import advent.of.code.util.Direction
import advent.of.code.util.Point
import java.util.*
import kotlin.math.max

class Tetris(private val infinityShapesGeneration: Sequence<(Point) -> Shape>, private val actions: Queue<Direction>) {
    private val alwaysActions = actions.toList()
    val peaksOfX: MutableMap<Int, Int> = mutableMapOf()
    private val maxX = 6
    fun play(rocks: Int = 2022): Int {
        return infinityShapesGeneration
            .take(rocks)
            .fold(Point(2, 4)) {  newShapeStartPosition, nextShapeFollowingPosition ->
                playShape(
                    nextShapeFollowingPosition(newShapeStartPosition),
                )
            }.y - 2
    }

    private fun playShape(shape: Shape): Point {
        moveShapeToRestPosition(shape)
        shape.getPoints()
            .forEach { peaksOfX.merge(it.x, it.y) { x1, x2 -> max(x1, x2) } }
        return Point(2, shape.findMaxOfY() + 4)
    }

    private fun moveShapeToRestPosition(shape: Shape) {
        windPushShape(shape)
        if (canFall(shape)) {
            shape.move(Direction.DOWN)
                moveShapeToRestPosition(shape)

        }
    }

    private fun windPushShape(shape: Shape) {
        val nextWind = extractNewWind()
        if (canBeBlown(shape, nextWind)) {
            shape.move(nextWind)
        }
    }

    private fun extractNewWind(): Direction {
        if (actions.isEmpty()) {
            actions.addAll(alwaysActions)
        }
        return actions.poll()

    }

    private fun canBeBlown(shape: Shape, direction: Direction): Boolean {
        val minY = shape.getPoints().minOf { it.y }
        val maxShapeXPoint = shape.getPoints().filter { it.y == minY }.maxBy { it.x }
        val minShapeXPoint = shape.getPoints().filter { it.y == minY }.maxBy { it.x }
        return when (direction) {
            Direction.RIGHT -> maxShapeXPoint.x < maxX && peaksOfX.getOrDefault(
                maxShapeXPoint.x + 1,
                0
            ) < maxShapeXPoint.y

            Direction.LEFT -> shape.getPoints()
                .minOf { it.x } > 0 && peaksOfX.getOrDefault(minShapeXPoint.x - 1, 0) < minShapeXPoint.y

            else -> throw IllegalArgumentException("Should not happen : $direction")
        }
    }

    private fun canFall(shape: Shape): Boolean {
        return shape.getPoints().all { p ->
            peaksOfX.getOrDefault(p.x, 0) + 1 < p.y
        }
    }

    fun getPeak(): Int {
        return this.peaksOfX.maxOf { it.value }
    }
}