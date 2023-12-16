package advent.of.code.year2023.day16

import advent.of.code.util.*

enum class Element {
    SPACE, MIRROR, SPLITTER
}

class LavaFacility(private val elements: Map<Point, Pair<Element, Char>>) {
    private val maxX = elements.findMaxX()
    private val maxY = elements.findMaxY()

    private val energisedFields: MutableSet<Pair<Point, Direction>> = mutableSetOf()

    fun fireLaser(originalDirection: Direction, startAt: Point): List<Point> {
        var direction = originalDirection
        var nextPoint = startAt.copy()
        val visitedSpace = mutableListOf<Point>()

        while (true) {
            nextPoint = nextPoint.recreateByDirectionReverse(direction)
            if (nextPoint.y < 0 || nextPoint.x < 0 || nextPoint.y > maxY || nextPoint.x > maxX) {
                return visitedSpace
            }
            if(energisedFields.contains(nextPoint to direction)){
                return energisedFields.map { it.first }
            }
            visitedSpace.add(nextPoint)
            energisedFields.add(nextPoint to direction)

            val element = elements[nextPoint]!!
            if (element.first == Element.SPACE) {
                continue
            }
            if (element.first == Element.SPLITTER) {
                if (element.second == '|') {
                    if ((direction == Direction.UP || direction == Direction.DOWN)) {
                        continue
                    } else {
                        return visitedSpace + fireLaser(Direction.UP, nextPoint) + fireLaser(Direction.DOWN, nextPoint)
                    }
                }
                if (element.second == '-') {
                    if ((direction == Direction.LEFT || direction == Direction.RIGHT)) {
                        continue
                    } else {
                        return visitedSpace + fireLaser(Direction.LEFT, nextPoint) + fireLaser(
                            Direction.RIGHT,
                            nextPoint
                        )
                    }
                }
                throw IllegalArgumentException("Should not happen")

            }

            if (element.first == Element.MIRROR) {
                if (element.second == '\\') {
                    val nextDirection = when (direction) {
                        Direction.UP -> Direction.LEFT
                        Direction.LEFT -> Direction.UP
                        Direction.DOWN -> Direction.RIGHT
                        Direction.RIGHT -> Direction.DOWN
                    }
                    direction = nextDirection
                    continue
                }
                if (element.second == '/') {
                    val nextDirection = when (direction) {
                        Direction.UP -> Direction.RIGHT
                        Direction.LEFT -> Direction.DOWN
                        Direction.DOWN -> Direction.LEFT
                        Direction.RIGHT -> Direction.UP
                    }
                    direction = nextDirection
                    continue
                }
                throw IllegalArgumentException("Shoild not happen")
            }
            throw IllegalArgumentException("Shoild not happen")
        }

    }


}