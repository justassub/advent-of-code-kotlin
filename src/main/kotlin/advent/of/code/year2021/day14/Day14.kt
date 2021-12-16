package advent.of.code.year2021.day14

import advent.of.code.year2021.ContentReader

class Day14 {
    fun task1(lines: List<String>, text: String, steps: Int): Long {
        val polymers = lines
            .map { PolymerBuilder.buildPremiumPurchaseAggregate(it) }
            .toSet()
        val pairs = text.windowed(2)
            .groupingBy { it }
            .eachCount()
            .mapValues { it.value.toLong() }

        val firstAndLast = listOf(text.first(), text.last())

        val data = (0..steps)
            .fold(pairs) { updatedValues, _ -> StepRunnerNew(updatedValues, polymers).runAllRules() }

        val countedChars = groupByCharCount(data)
            .mapValues { (it.value + if (firstAndLast.contains(it.key)) 1 else 0) / 2 }

        return countedChars.maxOf { it.value } - countedChars.minOf { it.value }

    }

    private fun groupByCharCount(data: Map<String, Long>): Map<Char, Long> {
        val counter = mutableMapOf<Char, Long>()
        data.forEach { (t, c) ->
            run {
                counter.merge(t.first(), c) { c1, c2 -> c1 + c2 }
                counter.merge(t.last(), c) { c1, c2 -> c1 + c2 }
            }
        }
        return counter
    }
}


fun main() {
    val data = ContentReader.readFileAsLines(14).dropLast(1)
    val day14 = Day14()
    println(day14.task1(data, "VHCKBFOVCHHKOHBPNCKO", 9))
    println(day14.task1(data, "VHCKBFOVCHHKOHBPNCKO", 39))

}
