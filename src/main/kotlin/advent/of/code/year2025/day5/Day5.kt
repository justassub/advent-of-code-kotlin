package advent.of.code.year2025.day5

import advent.of.code.ContentReader.readFileAsLines
import advent.of.code.util.printResult

fun main() {
    val text = readFileAsLines(2025, 5)

    val emptyLine = text.indexOfFirst { it.isBlank() }


    val idRanges = text.take(emptyLine)
        .map { it.split("-") }
        .map { IngridientRange(it.first().toLong(), it.last().toLong()) }
    val ingridients = text.drop(emptyLine + 1)

    ingridients
        .filter { idRanges.any { range -> range.containts(it.toLong()) } }
        .size
        .printResult()

}

data class IngridientRange(
    val minRangeInc: Long,
    val maxRangeInc: Long,
) {
    fun containts(value: Long): Boolean {
        return value in minRangeInc..maxRangeInc
    }
}

