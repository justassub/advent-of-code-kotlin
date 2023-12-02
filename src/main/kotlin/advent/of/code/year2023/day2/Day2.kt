package advent.of.code.year2023.day2

import advent.of.code.ContentReader

val rules = mapOf(
    COLOUR.RED to 12,
    COLOUR.GREEN to 13,
    COLOUR.BLUE to 14
)

fun main() {
    val data = ContentReader.readFileAsLines(2023, 2)
        .map { GameBuilder.createGameFromLine(it) }


    data
        .filter { GameValidator.isGameValid(it, rules) }
        .sumOf { it.id }
        .run { println(this) }
}