package advent.of.code.year2021.day7

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day7Test {
    private val data = "16,1,2,0,4,2,7,1,2,14".split(",").map { it.toInt() }
    private val day7 = Day7()

    @Test
    fun part1Example1() {
        Assertions.assertEquals(37, day7.part1(data))
    }

    @Test
    fun part2Example1() {
        Assertions.assertEquals(168, day7.part2(data))
    }


}
