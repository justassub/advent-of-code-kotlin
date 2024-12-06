package advent.of.code.util

import advent.of.code.year2022.day17.Movable
import advent.of.code.year2022.day9.Position
import kotlin.math.max
import kotlin.math.min

data class BigPoint(val x: Long, val y: Long)

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

fun Point.recreateByDirectionReverse(direction: ALLDirections): Point {
    return when (direction) {
        ALLDirections.UP -> recreateVertically(-1)
        ALLDirections.RIGHT -> recreateHorizontally(1)
        ALLDirections.DOWN -> recreateVertically(1)
        ALLDirections.LEFT -> recreateHorizontally(-1)
        ALLDirections.UP_RIGHT -> recreateVertically(-1).recreateHorizontally(1)
        ALLDirections.UP_LEFT -> recreateVertically(-1).recreateHorizontally(-1)
        ALLDirections.DOWN_RIGHT -> recreateVertically(1).recreateHorizontally(1)
        ALLDirections.DOWN_LEFT -> recreateVertically(1).recreateHorizontally(-1)
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


/**
 * returns points that points collect by sliding to direction by map and info if it reached to obstacle or end of map
 */
fun slideToDirectionByMapToObstacleHistory(
    currentPosition: Point,
    direction: Direction,
    obs: Set<Point>,
    maxX: Int,
    maxY: Int,
    minX: Int,
    minY: Int
): Pair<Pair<Point, Boolean>, Set<Point>> {
    val nextObs = obs.findNextObs(currentPosition, direction)

    if (nextObs == null) {
        val nextMapLocation = createMapStopPoint(currentPosition, direction, maxX, maxY, minX, minY)
        return (nextMapLocation to true) to createHistoryOfPointMigration(currentPosition, nextMapLocation)
    }
    val reverseDirection = direction.reverseDirection()
    val location = nextObs.recreateByDirectionReverse(reverseDirection)
    return (location to false) to createHistoryOfPointMigration(currentPosition, location)
}


/**
 * returns points that points collect by sliding to direction by map and info if it reached to obstacle or end of map
 */
fun slideToDirectionByMapToObstacleNoHistory(
    currentPosition: Point,
    direction: Direction,
    obs: Set<Point>,
    maxX: Int,
    maxY: Int,
    minX: Int,
    minY: Int
): Pair<Point, Boolean> {
    val nextObs = obs.findNextObs(currentPosition, direction)


    if (nextObs == null) {
        val nextMapLocation = createMapStopPoint(currentPosition, direction, maxX, maxY, minX, minY)

        return nextMapLocation to true
    }

    val reverseDirection = direction.reverseDirection()
    return nextObs.recreateByDirectionReverse(reverseDirection) to false
}

fun createMapStopPoint(
    current: Point,
    direction: Direction,
    maxX: Int,
    maxY: Int,
    minX: Int,
    minY: Int
): Point {
    return when (direction) {
        Direction.UP -> Point(current.x, minY)
        Direction.DOWN -> Point(current.x, maxY)
        Direction.LEFT -> Point(current.y, minX)
        Direction.RIGHT -> Point(current.y, maxX)
    }
}

fun Set<Point>.findNextObs(location: Point, direction: Direction): Point? {
    return when (direction) {
        Direction.UP -> this.filter { it.x == location.x && it.y < location.y }.maxByOrNull { it.y }
        Direction.DOWN -> this.filter { it.x == location.x && it.y > location.y }.minByOrNull { it.y }
        Direction.LEFT -> this.filter { it.y == location.y && it.x < location.x }.maxByOrNull { it.x }
        Direction.RIGHT -> this.filter { it.y == location.y && it.x > location.x }.minByOrNull { it.x }
    }
}


fun createHistoryOfPointMigration(point: Point, point2: Point): Set<Point> {
    return (min(point.x, point2.x)..max(point.x, point2.x))
        .flatMap { x ->
            ((min(point.y, point2.y))..max(point.y, point2.y))
                .map { y -> Point(x, y) }
        }.toSet()

}


