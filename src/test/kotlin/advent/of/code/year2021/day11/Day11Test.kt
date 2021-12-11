package advent.of.code.year2021.day11

import advent.of.code.year2021.ContentReader.readFileAsLines
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource


class Day11Test {
    private val data = readFileAsLines(11).filter { it.isNotEmpty() }
    private val day11 = Day11()

    @ParameterizedTest
    @CsvSource(
        "2,35",
        "0,0",
        "1,0",
        "100,1656"
    )
    fun part1Example1(days: Int, expectedFlash: Long) {
        Assertions.assertEquals(expectedFlash, day11.part1(data,days))
    }

    @Test
    fun part2Example1() {
        Assertions.assertEquals(195, day11.part2(data))
    }

}
