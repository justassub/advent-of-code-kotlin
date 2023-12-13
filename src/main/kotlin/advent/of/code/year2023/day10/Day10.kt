package advent.of.code.year2023.day10

import advent.of.code.util.PointBuilder.createPointsFromFile
import advent.of.code.util.recreateVertically

fun main() {
    val pipes = createPointsFromFile(2023, 10)
        .filter { it.value != '.' }

    val startPosition = pipes.filter { it.value == 'S' }.firstNotNullOf { it.key }.recreateVertically(-1)
    val calculator = PipeDistanceCalculator(
        pipes,
        startPosition to pipes[startPosition]!!
    )
    println(calculator.calculateHighestDistance())
}

