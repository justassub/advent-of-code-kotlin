package advent.of.code.year2023.day24

import advent.of.code.ContentReader
import advent.of.code.util.BigPoint
import advent.of.code.util.createPossibleCombinationsWithoutSelf
import advent.of.code.util.printResult

fun main() {
    val lines = ContentReader.readFileAsLines(2023, 24)
        .map { createHailStone(it) }
        .createPossibleCombinationsWithoutSelf()

    val testAreaX = 200000000000000
    val textAreaY = 400000000000000

    lines.count { isIntersecting(it, testAreaX, textAreaY) }
        .printResult()
}

fun isIntersecting(pair: Pair<Hailstone, Hailstone>, testAreaMin: Long, textAreaMax: Long): Boolean {
    val (first, second) = pair
    val iX = (second.b - first.b) / (first.slope - second.slope)
    val iY = first.slope * iX + first.b

    val t1 = (iX - first.start.x) / first.deltaX
    val t2 = (iX - second.start.x) / second.deltaX


    return when {
        (t1 < 0 || t2 < 0) -> false
        first.slope == second.slope -> false
        else -> (testAreaMin <= iX && textAreaMax >= iX) && (testAreaMin <= iY && textAreaMax >= iY)
    }
}

private fun createHailStone(line: String): Hailstone {
    val startAndDelta = line.split(" @ ")
    val startPoint = startAndDelta.first().split(", ")
        .let { BigPoint(it[0].toLong(), it[1].toLong()) }

    val delta = startAndDelta.last().split(",").map { it.trim() }
        .let { it[0].toLong() to it[1].toLong() }

    return Hailstone(startPoint, delta.first, delta.second)
}