package advent.of.code.year2021.day9

import advent.of.code.year2021.ContentReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day9Test {
    private val data = ContentReader.readFileAsLines(9)
    private val day9=Day9()

    @Test
    fun part1Example1() {
        Assertions.assertEquals(15, day9.part1(data))
    }
    @Test
    fun part2Example1() {
        Assertions.assertEquals(1134, day9.part2(data))
    }
}
