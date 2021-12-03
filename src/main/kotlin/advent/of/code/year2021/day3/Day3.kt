package advent.of.code.year2021.day3

import advent.of.code.year2021.ContentReader

class Day3 {
    fun part1(data: List<String>): Long {
        val elementsFrequency = groupDataByFrequency(data)


        val gammaRates = elementsFrequency
            .map { Pair(it.key, it.value.maxByOrNull { a -> a.value }!!.key) }

        val epsilonRate = elementsFrequency
            .map { Pair(it.key, it.value.minByOrNull { a -> a.value }!!.key) }


        val gamma = combineRates(gammaRates)
        val epsilon = combineRates(epsilonRate)

        return gamma.toLong(2) * epsilon.toLong(2)
    }

    private fun groupDataByFrequency(data: List<String>): Map<Int, Map<Char, Int>> {
        return data.flatMap { it.withIndex() }
            .groupBy(keySelector = { it.index }, valueTransform = { it.value })
            .mapValues { entry -> entry.value.groupingBy { it }.eachCount() }
    }

    private fun combineRates(rates: List<Pair<Int, Char>>): String {
        return rates
            .sortedBy { it.first }
            .map { it.second }
            .joinToString(separator = "")
    }
}

fun main() {
    val data = ContentReader.readFileAsLines(3)
    val day3 = Day3()
    val result1 = day3.part1(data)
    println(result1)
}