package advent.of.code.year2023.day4

import advent.of.code.ContentReader
import kotlin.math.pow

fun main() {
    val data = ContentReader.readFileAsLines(2023, 4)
        .map { ScratchcardBuilder.createGameFromLine(it) }

    data
        .map { it.calculateWinningNumbers() }
        .filter { it.isNotEmpty() }
        .sumOf { calculateScores(it) }
        .run { println(this) }



    data.associate { it.id to it.calculateWinningNumbers().size }
        .run {
            println(
                calculateWinningCardsRecursion(this)
            )
        }


}

private fun calculateScores(numbers: List<Int>) = 2.0.pow((numbers.size - 1).toDouble())


private fun calculateWinningCardsRecursion(originalData: Map<Int, Int>): Long {
    val cache: MutableMap<Int, Long> = mutableMapOf()

    fun winningNumbersCalculate(number: Int): Long {
        return (number + 1..number + originalData[number]!!)
            .sumOf { cache[it]!! }
            .apply { cache[number] = this + originalData[number]!! }

    }
    originalData
        .keys
        .reversed()
        .forEach { winningNumbersCalculate(it) }
    return cache.values.sum() + originalData.size
}