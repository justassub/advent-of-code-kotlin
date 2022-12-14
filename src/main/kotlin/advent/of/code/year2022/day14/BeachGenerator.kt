package advent.of.code.year2022.day14

import kotlin.math.max
import kotlin.math.min

object BeachGenerator {

    fun generateBeach(lines: List<String>): Map<BeachPoint, BeachObject> {
        return lines
            .flatMap { generateStones(it) }
            .associateWith { BeachObject.ROCK }
    }

    private fun generateStones(line: String): MutableSet<BeachPoint> {
        val positions = line.split(" -> ")
            .map { createPosition(it) }
        return positions
            .fold(Pair(mutableSetOf<BeachPoint>(), positions.first())) { acc, f ->
                createAllStones(
                    acc.second,
                    f,
                    acc.first
                )
            }
            .first

    }

    private fun createPosition(text: String): BeachPoint {
        return text.split(",")
            .let { BeachPoint(it.first().toInt(), it.last().toInt()) }
    }

    private fun createAllStones(
        start: BeachPoint,
        finish: BeachPoint,
        stones: MutableSet<BeachPoint>
    ): Pair<MutableSet<BeachPoint>, BeachPoint> {
        if (start.x == finish.x) {
            (start.y..finish.y)
        }

        (min(start.x, finish.x)..max(start.x, finish.x))
            .flatMap { x -> (min(start.y, finish.y)..max(start.y, finish.y)).map { BeachPoint(x, it) } }
            .let { stones.addAll(it) }

        return Pair(stones, finish);
    }
}
