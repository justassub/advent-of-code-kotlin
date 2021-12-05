package advent.of.code.year2021.day5

import advent.of.code.year2021.ContentReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day5Test {
    private val day5 = Day5()
    private val data = ContentReader.readFileAsLines(5)

    @Test
    fun part1Example() {
        Assertions.assertEquals(5, day5.task1(data))
    }
}