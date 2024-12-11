package advent.of.code.year2024.day11

import advent.of.code.ContentReader
import advent.of.code.util.printResult

fun main() {
    val input = ContentReader.readFileAsMultipleNumbersInLine(2024, 11, " ")
        .flatMap { it.map { it.toLong() } }
    val result1 = produceStones(input, 25)
    printResult(result1)
}

private fun printResult(result: Map<StoneId, Long>) {
    result
        .values
        .sum()
        .printResult()
}


fun produceStones(stones: List<StoneId>, days: Int): Map<StoneId, Long> {
    var stonesCounter = stones.groupingBy { it }.eachCount().mapValues { it.value.toLong() }.toMutableMap()
    for (day in 1..days) {
        val aggregation = mutableMapOf<StoneId, Long>()
        stonesCounter.entries
            .forEach {
                val (stoneId, count) = it
                fillStones(aggregation, stoneId, count)
            }
        stonesCounter = aggregation
    }
    return stonesCounter
}

fun fillStones(stonesCounter: MutableMap<StoneId, Long>, stoneId: StoneId, count: Long) {
    fun merge(stoneId: StoneId) {
        stonesCounter.merge(stoneId, count) { old, new -> old + new }
    }
    when {
        stoneId == 0L -> merge(1)
        stoneId.toString().length % 2 == 0 -> {
            val (spliNumber1, splitNumber2) = stoneId.toString()
                .let {
                    it.substring(0, it.length / 2) to it.substring(it.length / 2)
                }
            merge(spliNumber1.toLong())
            merge(splitNumber2.toLong())
        }

        else -> merge(stoneId * 2024)
    }

}

private typealias StoneId = Long