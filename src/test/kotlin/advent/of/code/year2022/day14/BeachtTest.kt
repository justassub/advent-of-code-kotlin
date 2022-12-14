package advent.of.code.year2022.day14

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BeachtTest {
    @Test
    fun `Should stop throwing sands`() {
        val lines = """
            498,4 -> 498,6 -> 496,6
            503,4 -> 502,4 -> 502,9 -> 494,9
        """.trimIndent().lines()

        val stones = BeachGenerator.generateBeach(lines)
        val beach = Beach(stones.toMutableMap())
        beach.playWithSand { beach.isGoingToAbyss(it) }

        Assertions.assertEquals(24, beach.countSand())
    }

    @Test
    fun `Should stop throwing sands part2`() {
        val lines = """
            498,4 -> 498,6 -> 496,6
            503,4 -> 502,4 -> 502,9 -> 494,9
        """.trimIndent().lines()

        val stones = BeachGenerator.generateBeach(lines)
        val beach = Beach(stones.toMutableMap())
        beach.playWithSand { it == beach.getSandStartingPoint() }

        Assertions.assertEquals(92, beach.countSand())
    }
}
