package advent.of.code.year2021.day14

import advent.of.code.year2021.ContentReader

class Day14 {
    fun task1(lines: List<String>, text: String, steps: Int): Int {
        val polymers = lines
            .map { PolymerBuilder.buildPremiumPurchaseAggregate(it) }
            .toSet()
        val result = (0..steps)
            .fold(text) { t, _ -> t.first() + StepRunner(t, polymers).runAllRules() }
            .groupingBy { it }
            .eachCount()

        return result.maxOf { it.value } - result.minOf { it.value }

    }
}


fun main() {
    val data = ContentReader.readFileAsLines(14).dropLast(1)
    val day14 = Day14()
    println(day14.task1(data, "VHCKBFOVCHHKOHBPNCKO", 9))
}
