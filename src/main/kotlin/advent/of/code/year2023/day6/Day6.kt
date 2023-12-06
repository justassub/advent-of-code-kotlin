package advent.of.code.year2023.day6

import advent.of.code.util.multiply

fun main() {
    val data = listOf(
        40L to 277L,
        82L to 1338L,
        91L to 1349L,
        66L to 1063L
    )
    data
        .map { calculatePossibleWays(it.first, it.second) }
        .multiply()
        .run { println(this) }


    val time = data.map { it.first }
        .joinToString("")
        .toLong()

    val distance = data.map { it.second }
        .joinToString("")
        .toLong()

    calculatePossibleWays(time,distance)
        .run { println(this) }

}

private fun calculatePossibleWays(totalSeconds: Long, requiredDistance: Long): Int {
    val maxDistanceTime = totalSeconds/2

    val possibleWays = generateSequence(maxDistanceTime) { it.minus(1) }
        .takeWhile { it * (totalSeconds - it) > requiredDistance }
        .count()

    return if (totalSeconds % 2 == 0L) {
        possibleWays * 2 - 1
    } else {
        possibleWays * 2
    }
}


