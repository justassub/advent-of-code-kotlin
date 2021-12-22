package advent.of.code.year2021.day22

import advent.of.code.year2021.ContentReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day22Test {
    val lines = ContentReader.readFileAsLines(22)
    val day22 = Day22()

    @Test
    fun part1Example1() {
        val lines = """
            on x=10..12,y=10..12,z=10..12
            on x=11..13,y=11..13,z=11..13
            off x=9..11,y=9..11,z=9..11
            on x=10..10,y=10..10,z=10..10
        """.trimIndent()
        Assertions.assertEquals(39, day22.task1(lines.split(System.lineSeparator())))
    }
    @Test
    fun part1Example2() {

        Assertions.assertEquals(590784, day22.task1(lines))
    }

}
