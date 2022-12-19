package advent.of.code.year2022.day17

import advent.of.code.util.Direction
import advent.of.code.util.Point
import java.util.*

class Tetris(private val infinityShapesGeneration: Sequence<(Point) -> Shape>, private val actions: Queue<Direction>) {
    private val alwaysActions = actions.toList()
    val peaksOfX: MutableMap<Int, MutableSet<Int>> = (0..6).associateWith { mutableSetOf(0) }
        .toMutableMap()
    private val maxX = 6
    fun play(rocks: Int = 2022): Int {
        return infinityShapesGeneration
            .take(rocks)
            .fold(Point(2, 4)) { newShapeStartPosition, nextShapeFollowingPosition ->
                playShape(
                    nextShapeFollowingPosition(newShapeStartPosition),
                )
            }.y - 2
    }

    private fun playShape(shape: Shape): Point {
        moveShapeToRestPosition(shape)
        shape.getPoints()
            .forEach { peaksOfX.getValue(it.x).add(it.y) }
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
        val minX = shape.getPoints().minOf { it.x }
        val maxXShape = shape.getPoints().maxOf { it.x }
        val maxShapeXPoints = shape.getPoints().filter { it.x == maxXShape }
        return when (direction) {
            Direction.RIGHT -> maxXShape < maxX && maxShapeXPoints.none {
                peaksOfX.getOrDefault(it.x + 1, emptySet()).contains(it.y)
            }

            Direction.LEFT -> minX > 0 && maxShapeXPoints.none {
                peaksOfX.getOrDefault(it.x - 1, emptySet()).contains(it.y)
            }


            else -> throw IllegalArgumentException("Should not happen : $direction")
        }
    }

    private fun canFall(shape: Shape): Boolean {
        return shape.getPoints().none { p ->
            peaksOfX.getValue(p.x).contains(p.y - 1)
        }

    }

    fun getPeak(): Int {
        return this.peaksOfX.maxOf { it -> it.value.maxOf { it } }
    }
}