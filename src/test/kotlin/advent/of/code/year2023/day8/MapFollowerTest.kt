package advent.of.code.year2023.day8

import advent.of.code.util.LeftOrRight
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MapFollowerTest {

    @Test
    fun `Should generate path correctly`() {
        val minSteps = MapFollower(
            mapOf(
                "AAA" to ("BBB" to "CCC"),
                "CCC" to ("ZZZ" to "GGG")
            ),
            listOf(LeftOrRight.RIGHT, LeftOrRight.LEFT)
        ).calculateMinSteps("ZZZ")


        assertEquals(
            2, minSteps
        )
    }

    @Test
    fun `Should generate path correctly 2`() {
        val minSteps = MapFollower(
            mapOf(
                "AAA" to ("BBB" to "BBB"),
                "BBB" to ("AAA" to "ZZZ"),
                "ZZZ" to ("ZZZ" to "ZZZ")
            ),
            listOf(LeftOrRight.LEFT, LeftOrRight.LEFT, LeftOrRight.RIGHT)
        ).calculateMinSteps("ZZZ")


        assertEquals(
            6, minSteps
        )
    }
}