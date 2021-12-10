package advent.of.code.year2021.day10

import advent.of.code.year2021.ContentReader.readFileAsLines
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day10Test {
    private val data = readFileAsLines(10).filter { it.isNotEmpty() }
    private val day10 = Day10()

    @Test
    fun part1Example1() {
        Assertions.assertEquals(26397, day10.part1(data))
    }
    @Test
    fun part1Example2() {
        Assertions.assertEquals(288957, day10.part2(data))
    }
}
