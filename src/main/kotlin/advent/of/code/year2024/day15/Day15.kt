package advent.of.code.year2024.day15

import advent.of.code.ContentReader
import advent.of.code.util.*

fun main() {
    val points = PointBuilder.createPointsFromFile(2024, 15)

    val directions = ContentReader.readFileAsLines(2024, 155)
        .flatMap { it.map { it.createDirection() } }

    val playerPoint = points.filter { it.value == '@' }.firstNotNullOf { it.key }

    val gameMap = points.map { it.key to it.toPointType() }
        .filter { it.second != null }
        .associate { it.first to it.second!! }
        .toMutableMap()


    val player = MovingObject(playerPoint)
    gameMap[playerPoint] = player

    directions.forEach {
        player.canMoveAndPush(gameMap, it)
    }
    gameMap
        .filter { it.value is MovingObject && it.value != player }
        .map { it.key }
        .sumOf { 100 * it.y + it.x }
        .printResult()
}

fun Map.Entry<Point, Char>.toPointType(): PointType? {
    return when (this.value) {
        '#' -> Wall(this.key)
        'O', '@' -> MovingObject(this.key)
        '.' -> null
        else -> throw IllegalArgumentException("Bad character $this")
    }
}


abstract class PointType {

    abstract fun canMoveAndPush(map: MutableMap<Point, PointType>, direction: Direction): Boolean
}

class Wall(val point: Point) : PointType() {
    override fun canMoveAndPush(map: MutableMap<Point, PointType>, direction: Direction): Boolean = false
}

class MovingObject(val point: Point) : PointType() {
    override fun canMoveAndPush(map: MutableMap<Point, PointType>, direction: Direction): Boolean {
        val nextPoint = point.recreateByDirectionReverse(direction)
        val nextConstruction = map[nextPoint]
        if (nextConstruction == null) {
            moveAndChangeMap(direction, map)
            return true
        } else {
            if (nextConstruction.canMoveAndPush(map, direction)) {
                moveAndChangeMap(direction, map)
                return true
            } else {
                return false
            }
        }
    }

    private fun moveAndChangeMap(
        direction: Direction,
        map: MutableMap<Point, PointType>
    ) {
        map.remove(point)
        point.moveReverse(direction)
        map[point] = this
    }
}


