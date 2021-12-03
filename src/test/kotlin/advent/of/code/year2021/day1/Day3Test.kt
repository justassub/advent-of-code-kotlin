package advent.of.code.year2021.day1

import advent.of.code.year2021.ContentReader
import advent.of.code.year2021.day3.Day3
import advent.of.code.year2021.day3.OxygenCandidate
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day3Test {
    private val day3 = Day3()
    private val data = ContentReader.readFileAsLines(3)

    @Test
    fun part1ResultCorrectAsExample() {
        Assertions.assertEquals(198, day3.part1(data))
    }

    @Test
    fun part2FindOxygenNumberCorrect() {
        val candidates = data.map { OxygenCandidate(it) }
        Assertions.assertEquals(
            "10111",
            day3.findRating(candidates) { index -> day3.findDominatingSymbol(index, candidates) })
    }

    @Test
    fun part2FindScrubberNumberCorrect() {
        val candidates = data.map { OxygenCandidate(it) }
        Assertions.assertEquals(
            "01010",
            day3.findRating(candidates) { index -> day3.findSubmissiveSymbol(index, candidates) })
    }

    @Test
    fun part2ResultCorrectAsExample() {
        Assertions.assertEquals(230, day3.part2(data))
    }
}