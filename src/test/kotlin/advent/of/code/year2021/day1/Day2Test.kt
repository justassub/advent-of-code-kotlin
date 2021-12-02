package advent.of.code.year2021.day1

import advent.of.code.year2021.ContentReader
import advent.of.code.year2021.day2.Day2
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day2Test {
    private val day2 = Day2()
    private val data = ContentReader.readFileAsLines(2)
    private val submarineCommands = Day2.parseCommands(data)

    @Test
    fun part1ResultCorrectAsExample() {
        Assertions.assertEquals(150, day2.part1(submarineCommands))
    }
    @Test
    fun part2ResultCorrectAsExample() {
        Assertions.assertEquals(900, day2.part2(submarineCommands))
    }
}