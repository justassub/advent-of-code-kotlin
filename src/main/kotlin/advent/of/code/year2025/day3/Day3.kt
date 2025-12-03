package advent.of.code.year2025.day3

import advent.of.code.ContentReader

fun main() {
    val banks = ContentReader.readFileAsLines(2025, 3)
        .map { l -> l.map { it.digitToInt() } }
        .map { Bank(it) }

    println(banks.sumOf { it.findMaximumJoltOfNumbers(2) })
    println(banks.sumOf { it.findMaximumJoltOfNumbers(12) })

}

private data class Bank(private val jolts: List<Int>) {

    fun findMaximumJoltOfNumbers(totalNumber: Int): Long {

        var text = ""
        var currentIndex = 0
        for (i in totalNumber - 1 downTo 0) {
            val nextMaxVolt = extractNextMaxVolt(currentIndex, i)
            text += nextMaxVolt.value.toString()
            currentIndex = nextMaxVolt.index + 1
        }
        return text.toLong()


    }

    private fun extractNextMaxVolt(
        startIndex: Int,
        numbersLeft: Int
    ): IndexedValue<Int> {
        return jolts
            .withIndex()
            .take(jolts.size - numbersLeft)
            .drop(startIndex)
            .maxBy { it.value }
    }
}