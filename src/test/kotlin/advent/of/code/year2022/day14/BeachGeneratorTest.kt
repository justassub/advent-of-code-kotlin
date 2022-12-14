package advent.of.code.year2022.day14

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BeachGeneratorTest {

    @Test
    fun `should create proper Stones`() {
        val lines = """
            498,4 -> 498,6 -> 496,6
            503,4 -> 502,4 -> 502,9 -> 494,9
        """.trimIndent().lines()

        val result = BeachGenerator.generateBeach(lines)
        Assertions.assertEquals(20, result.size)
    }

    @Test
    fun `should create proper Stones with bottom`() {
        val lines = """
            498,4 -> 498,6 -> 496,6
            503,4 -> 502,4 -> 502,9 -> 494,9
        """.trimIndent().lines()

        val result = BeachGenerator.generateBeach(lines).toMutableMap()
        val beach = Beach(result, true)
        beach.playWithSand { it==beach.getSandStartingPoint() }
        Assertions.assertEquals(92, beach.countSand())
    }

}
