package advent.of.code.year2023.day4

import advent.of.code.ContentReader
import kotlin.math.pow

fun main() {
    ContentReader.readFileAsLines(2023, 4)
        .map { ScratchcardBuilder.createGameFromLine(it) }
        .map { it.calculateWinningNumbers() }
        .filter { it.isNotEmpty() }
        .sumOf { calculateScores(it) }
        .run { println(this) }


}

private fun calculateScores(numbers: List<Int>) = 2.0.pow((numbers.size - 1).toDouble())
