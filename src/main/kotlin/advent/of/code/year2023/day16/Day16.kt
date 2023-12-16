package advent.of.code.year2023.day16

import advent.of.code.util.Direction
import advent.of.code.util.Point
import advent.of.code.util.PointBuilder
import advent.of.code.util.printResult


fun main() {

    fun Char.createElementFromChar(): Element = when (this) {
        '.' -> Element.SPACE
        '\\', '/' -> Element.MIRROR
        '|', '-' -> Element.SPLITTER
        else -> throw IllegalArgumentException("Bad character: $this")
    }

    val data = PointBuilder.createPointsFromFile(2023, 16)
        .mapValues { it.value.createElementFromChar() to it.value }

    val facility = LavaFacility(data)

    facility.fireLaser(Direction.RIGHT, Point(-1, 0))
        .distinct()
        .count()
        .printResult()
}