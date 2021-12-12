package advent.of.code.year2021.day12

import advent.of.code.year2021.ContentReader.readFileAsLines
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class Day12Test {
    private val data = readFileAsLines(12).filter { it.isNotEmpty() }
    private val day12 = Day12()


    @Test
    fun part1Example1() {
        Assertions.assertEquals(10, day12.part1(data))
    }

    @Test
    fun part2Example1() {
        Assertions.assertEquals(36, day12.part2(data))
    }

}
