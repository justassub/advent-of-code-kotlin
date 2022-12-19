package advent.of.code.util

import advent.of.code.year2022.day17.Movable
import advent.of.code.year2022.day9.Position

data class Point(var x: Int, var y: Int) : Movable {
    override fun move(direction: Direction) {
        when (direction) {
            Direction.UP -> this.y++
            Direction.RIGHT -> this.x++
            Direction.DOWN -> this.y--
            Direction.LEFT -> this.x--
        }
    }

}


fun Point.recreateHorizontally(amount: Int) = Point(this.x + amount, this.y)

fun Point.recreateVertically(amount: Int) = Point(this.x, this.y + amount)

operator fun Point.minus(point: Point) = Position(this.x - point.x, this.y - point.y)
operator fun Point.plus(point: Point) = Position(this.x + point.x, this.y + point.y)
