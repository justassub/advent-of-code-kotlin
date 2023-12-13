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

fun Point.recreateByDirectionReverse(direction: Direction): Point {
    return when (direction) {
        Direction.UP -> recreateVertically(-1)
        Direction.DOWN -> recreateVertically(1)
        Direction.LEFT -> recreateHorizontally(-1)
        Direction.RIGHT -> recreateHorizontally(1)
    }
}

operator fun Point.minus(point: Point) = Position(this.x - point.x, this.y - point.y)
operator fun Point.plus(point: Point) = Position(this.x + point.x, this.y + point.y)


fun Point.findNeighbours(): Set<Point> {
    return setOf(
        Point(this.x, this.y - 1),
        Point(this.x, this.y + 1),
        Point(this.x - 1, this.y),
        Point(this.x + 1, this.y)
    )
}

fun Point.findNeighboursDiagonal(): Set<Point> {
    return setOf(
        Point(this.x, this.y - 1),
        Point(this.x, this.y + 1),
        Point(this.x - 1, this.y),
        Point(this.x + 1, this.y),

        Point(this.x - 1, this.y - 1),
        Point(this.x - 1, this.y + 1),
        Point(this.x + 1, this.y - 1),
        Point(this.x + 1, this.y + 1),
    )
}
