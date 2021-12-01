package advent.of.code.year2021.day1

import advent.of.code.year2021.ContentReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day1Test {
    val day1 = Day1()
    val data = ContentReader.readFileAsNumbers(1)

    @Nested
    @DisplayName("Day 1 Part 1 test")
    inner class Day1Part1Test() {
        @Test
        fun assertCorrectResult() {
            val increases = day1.part1(data)
            Assertions.assertSame(7, increases)
        }
    }

    @Nested
    @DisplayName("Day 1 Part 2 test")
    inner class Day1Part2Test() {
        @Test
        fun assertCorrectResult() {
            val increases = day1.part2(data)
            Assertions.assertSame(5, increases)
        }
    }
}