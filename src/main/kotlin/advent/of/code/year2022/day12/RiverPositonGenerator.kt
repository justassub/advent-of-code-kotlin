package advent.of.code.year2022.day12

import advent.of.code.year2022.day9.Position

object RiverPositonGenerator {

    fun generateRiverPosition(lines: List<String>): Map<Position, Char> {
        return lines
            .flatMapIndexed { y, line ->
                line.mapIndexed { x, c -> Pair(Position(x, y), c) }
            }.toMap()
    }
}