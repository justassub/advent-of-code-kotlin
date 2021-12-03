package advent.of.code.year2021.day1

import advent.of.code.year2021.ContentReader
import advent.of.code.year2021.day3.Day3
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day3Test {
    private val day3 = Day3()
    private val data = ContentReader.readFileAsLines(3)

    @Test
    fun part1ResultCorrectAsExample() {
        Assertions.assertEquals(198, day3.part1(data))
    }

}