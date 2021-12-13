package advent.of.code.year2021.day13

import advent.of.code.year2021.ContentReader.readFileAsLines
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class Day13Test {
    private val data = readFileAsLines(13)

    private val emptyLineIndex = data.indexOf("")
    private val lines = data.take(emptyLineIndex)
    private val commands = data.drop(emptyLineIndex + 1).dropLast(1)

    private val day13 = Day13()


    @Test
    fun part1Example1() {
        Assertions.assertEquals(17, day13.task1(lines, commands))
    }


}
