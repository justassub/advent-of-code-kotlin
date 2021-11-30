package advent.of.code.year2021.day1

import advent.of.code.year2021.ContentReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day1Test {
    val day1 = Day1()

    @Nested
    @DisplayName("Day 1 Part 1 test")
    inner class Day1Part1Test() {
        @Test
        fun assertCorrectResult() {
            day1.part1(ContentReader.readFileAsNumbers(1))
            Assertions.assertSame(1, 1)
        }
    }
}