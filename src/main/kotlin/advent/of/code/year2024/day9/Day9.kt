package advent.of.code.year2024.day9

import advent.of.code.ContentReader
import advent.of.code.util.printResult

fun main() {
    val data = Amphipod(ContentReader.readFileAsText(2024, 9))
    calculateResult(data.compactBlocksFixed())
    calculateResult(data.compactBlocksFixedMoreStrick())
}

private fun calculateResult(data: List<Int?>) {
    data
        .withIndex()
        .filter { it.value != null }
        .sumOf { it.index.toLong() * it.value!! }
        .printResult()
}


