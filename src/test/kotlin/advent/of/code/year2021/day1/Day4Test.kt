package advent.of.code.year2021.day1

import advent.of.code.year2021.ContentReader
import advent.of.code.year2021.day4.Day4
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day4Test {
    private val day4 = Day4()
    private val data = ContentReader.readFileAsLines(4)
    val numbers = "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1"
        .split(",")
        .map { it.toInt() }

    @Test
    fun part1ResultCorrectAsExample() {

        Assertions.assertEquals(4512, day4.task1(data, numbers))
    }

    @Test
    fun part2ResultCorrectAsExample() {
        Assertions.assertEquals(1924, day4.task2(data, numbers))
    }


}