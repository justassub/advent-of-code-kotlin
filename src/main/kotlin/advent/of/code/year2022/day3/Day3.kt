package advent.of.code.year2022.day3

import advent.of.code.year2022.ContentReader2022

const val day = 3
fun main() {

    val originalLines = ContentReader2022.readFileAsLines(day)

    val part1Result = originalLines
        .map { RucksackMaker.makeRucksackBySplitInHalf(it).findCommonElement() }
        .sumOf { PriorityCalculator.funCalculatePriority(it) }

    val part2Result = originalLines
        .chunked(3)
        .map { Rucksack(it).findCommonElement() }
        .sumOf { PriorityCalculator.funCalculatePriority(it) }


    println(part1Result)

    println(part2Result)
}
