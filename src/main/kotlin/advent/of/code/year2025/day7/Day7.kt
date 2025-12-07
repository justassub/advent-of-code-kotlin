package advent.of.code.year2025.day7

import advent.of.code.util.Point
import advent.of.code.util.PointBuilder
import advent.of.code.util.recreateHorizontally
import advent.of.code.util.recreateVertically

fun main() {
    val map = PointBuilder.createPointsFromFile(2025, 7)
    var beams = map.filter { it.value == 'S' }.map { it.key }.toSet()
    val spikes = map.filter { it.value == '^' }.map { it.key }.toSet()

    var totalBreaks = 0

    val maxY = map.keys.maxOf { it.y }
    fun Collection<Point>.canMoveDown(): Boolean {
        return this.any { it.y < maxY }
    }
    while (beams.canMoveDown()) {
        val newBeams = beams.map { it.recreateVertically(1) }
            .flatMap {
                if (spikes.contains(it)) {
                    totalBreaks++
                    listOf(it.recreateHorizontally(1), it.recreateHorizontally(-1))
                } else {
                    listOf(it)
                }
            }.toSet()

        beams = newBeams
    }

    print(totalBreaks)
}