package advent.of.code.year2024.day4

import advent.of.code.util.*

const val XMAS = "XMAS"

fun main() {
    val input = PointBuilder.createPointsFromFile(2024, 4)

    val xStarts = input.filter { it.value == 'X' }

    ALLDirections.entries.sumOf { d -> xStarts.count { canCreateXMASInDirection(it.toPair(), d, input) } }
        .printResult()
}

fun canCreateXMASInDirection(
    entry: Pair<Point, Char>,
    d: ALLDirections,
    input: Map<Point, Char>
): Boolean {

    val nextPoint = entry.first.recreateByDirectionReverse(d)

    return when (val target = XMAS.getOrNull(XMAS.indexOf(entry.second) + 1)) {
        null -> true
        input[nextPoint] -> canCreateXMASInDirection(nextPoint to target, d, input)
        else -> false
    }

}