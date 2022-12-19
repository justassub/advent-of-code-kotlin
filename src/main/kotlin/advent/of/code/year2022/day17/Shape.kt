package advent.of.code.year2022.day17

import advent.of.code.util.Direction
import advent.of.code.util.Point
import advent.of.code.util.recreateHorizontally
import advent.of.code.util.recreateVertically


abstract class Shape(val start: Point) : Movable {
    abstract fun getPoints(): Set<Point>

    override fun move(direction: Direction) {
        getPoints().forEach { it.move(direction) }
    }

    fun findMaxOfY() = getPoints().maxOf { it.y }
}

data class MinusShape(private val firstPoint: Point) : Shape(firstPoint) {

    private val shapePoints = createPoints()
    private fun createPoints(): Set<Point> {
        val firstPoint = firstPoint
        val second = firstPoint.recreateHorizontally(1)
        val third = second.recreateHorizontally(1)
        val fourth = third.recreateHorizontally(1)
        return setOf(firstPoint, second, third, fourth)
    }

    override fun getPoints(): Set<Point> {
        return shapePoints
    }

}

data class PlusShape(private val firstPoint: Point) : Shape(firstPoint) {
    private val shapePoints = createPoints()

    private fun createPoints(): Set<Point> {
        val firstPoint = firstPoint.recreateHorizontally(1)
        val second = firstPoint.recreateVertically(1)
        val third = second.recreateHorizontally(1)
        val fourth = second.recreateHorizontally(-1)
        val fifth = second.recreateVertically(1)
        return setOf(firstPoint, second, third, fourth, fifth)
    }

    override fun getPoints(): Set<Point> = shapePoints

}

data class LShape(private val firstPoint: Point) : Shape(firstPoint) {
    private val shapePoints = createPoints()
    override fun getPoints(): Set<Point> = shapePoints

    private fun createPoints(): Set<Point> {
        val firstPoint = firstPoint
        val second = firstPoint.recreateHorizontally(1)
        val third = second.recreateHorizontally(1)
        val fourth = third.recreateVertically(1)
        val fifth = fourth.recreateVertically(1)
        return setOf(firstPoint, second, third, fourth, fifth)
    }

}

data class IShape(private val firstPoint: Point) : Shape(firstPoint) {
    private val shapePoints = createPoints()

    override fun getPoints(): Set<Point> = shapePoints
    private fun createPoints(): Set<Point> {
        val firstPoint = firstPoint
        val second = firstPoint.recreateVertically(1)
        val third = second.recreateVertically(1)
        val fourth = third.recreateVertically(1)
        return setOf(firstPoint, second, third, fourth)
    }
}

data class SquareShape(private val firstPoint: Point) : Shape(firstPoint) {
    private val shapePoints = createPoints()
    private fun createPoints(): Set<Point> {
        val firstPoint = firstPoint
        val second = firstPoint.recreateHorizontally(1)
        val third = firstPoint.recreateVertically(1)
        val fourth = third.recreateHorizontally(1)
        return setOf(firstPoint, second, third, fourth)
    }

    override fun getPoints(): Set<Point> = shapePoints
}

