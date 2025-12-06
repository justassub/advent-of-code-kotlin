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


    val mergedRanges = mergeRanges(idRanges)
    mergedRanges
        .sumOf { it.maxRangeInc - it.minRangeInc + 1 }
        .printResult()
}


private fun mergeRanges(ranges: List<IngridientRange>): List<IngridientRange> {
    if (ranges.isEmpty()) return emptyList()

    val sorted = ranges.sortedBy { it.minRangeInc }

    val result = mutableListOf<IngridientRange>()
    var current = sorted[0]

    for (i in 1 until sorted.size) {
        val next = sorted[i]
        if (next.minRangeInc <= current.maxRangeInc) {
            // They overlap — merge
            current = IngridientRange(
                minRangeInc = current.minRangeInc,
                maxRangeInc = maxOf(current.maxRangeInc, next.maxRangeInc)
            )
        } else {
            // No overlap — push current, move to next
            result.add(current)
            current = next
        }
    }

    // add last range
    result.add(current)
    return result
}

data class IngridientRange(
    val minRangeInc: Long,
    val maxRangeInc: Long,
) {
    fun containts(value: Long): Boolean {
        return value in minRangeInc..maxRangeInc
    }
}

