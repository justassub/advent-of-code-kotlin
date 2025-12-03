package advent.of.code.year2025.day3

import advent.of.code.ContentReader

fun main() {
    val banks = ContentReader.readFileAsLines(2025, 3)
        .map { l -> l.map { it.digitToInt() } }
        .map { Bank(it) }

    println(banks.sumOf { it.findMaximumJoltOfTwoNumbers() })


}

private data class Bank(private val jolts: List<Int>) {

    fun findMaximumJoltOfTwoNumbers(): Int {
        val firstDigitMaxNumber = jolts.take(jolts.size - 1)
            .withIndex()
            .maxBy { it.value }

        val secondMaxNumber = jolts
            .drop(firstDigitMaxNumber.index + 1)
            .max()

        return "${firstDigitMaxNumber.value}${secondMaxNumber}".toInt()

    }
}