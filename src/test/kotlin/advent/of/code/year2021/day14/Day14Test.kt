package advent.of.code.year2021.day14

import advent.of.code.year2021.ContentReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day14Test {
    val data = ContentReader.readFileAsLines(14).dropLast(1)
    val day14 = Day14()


    @Test
    fun part1Example1() {
        Assertions.assertEquals(1588, day14.task1(data, "NNCB", 9))
    }

    @Test
    fun part1Example2() {
        Assertions.assertEquals(2188189693529, day14.task1(data, "NNCB", 39))
    }
}
