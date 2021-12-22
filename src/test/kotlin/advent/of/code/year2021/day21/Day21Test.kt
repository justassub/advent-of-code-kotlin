package advent.of.code.year2021.day21

import advent.of.code.year2021.ContentReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day21Test {
    val lines = ContentReader.readFileAsLines(21)
    val day21 = Day21()

    @Test
    fun part1Example1() {
        Assertions.assertEquals(739785, day21.task1(lines))
    }

}
