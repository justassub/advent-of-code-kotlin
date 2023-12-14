package advent.of.code.year2023.day14

import advent.of.code.util.Direction
import advent.of.code.util.Point
import advent.of.code.util.PointBuilder

fun main() {
    val rocks = PointBuilder.createPointsFromFile(2023, 14)
        .filter { it.value != '.' }
        .map { createRock(it) }


    moveRocksFullTop(rocks)

    val maxY = rocks.maxOf { it.getCurrentPosition().y } + 1

    rocks
        .filterIsInstance<RoundRock>()
        .sumOf { maxY - it.getCurrentPosition().y }
        .apply { println(this) }
    println()
}

private fun moveRocksFullTop(allRocks: List<Rock>): List<Rock> {
    var allRocksThatNeedsMove = allRocks
        .filterIsInstance<RoundRock>()
        .filterNot { it.isStuck }
        .sortedBy { it.getCurrentPosition().y }

    while (allRocksThatNeedsMove.isNotEmpty()) {
        allRocksThatNeedsMove = allRocksThatNeedsMove
            .onEach { it.move(Direction.UP, allRocks) }
            .filterNot { it.isStuck }
    }

    return allRocksThatNeedsMove
}

private fun createRock(entry: Map.Entry<Point, Char>): Rock {
    return when (entry.value) {
        '#' -> SquareRock(entry.key)
        'O' -> RoundRock(entry.key)
        else -> throw IllegalArgumentException("Cant create rock from : ${entry.value}")
    }
}