package advent.of.code.year2023.day8

import advent.of.code.util.Builder
import advent.of.code.util.LeftOrRight
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MapConstructorTest {

    @Test
    fun `Should create map correctly`() {
        val map = MapConstructor.createInitialMap(listOf("ZZZ = (ABC, GCD)"))

        assertEquals(
            mapOf("ZZZ" to ("ABC" to "GCD")),
            map
        )
    }

    @Test
    fun `Should create directions correctly `() {
        val directions = Builder.leftOrRightBuilderSingleLine("RL", 'L', 'R')

        assertEquals(
            listOf(LeftOrRight.RIGHT, LeftOrRight.LEFT),
            directions
        )
    }
}