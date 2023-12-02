package advent.of.code.year2023.day2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GameBuilderTest {

    @Test
    fun `Should create correct game from single line`() {
        val line = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"

        val result = GameBuilder.createGameFromLine(line)

        assertEquals(1, result.id)
        assertEquals(3, result.sets.size)
        assertEquals(
            listOf(GameSubset(COLOUR.BLUE, 3), GameSubset(COLOUR.RED, 4)),
            result.sets[0].subset
        )

        assertEquals(
            listOf(GameSubset(COLOUR.RED, 1), GameSubset(COLOUR.GREEN, 2), GameSubset(COLOUR.BLUE, 6)),
            result.sets[1].subset
        )

        assertEquals(
            listOf(GameSubset(COLOUR.GREEN, 2)),
            result.sets[2].subset
        )
    }
}