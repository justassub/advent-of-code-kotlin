package advent.of.code.year2023.day2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RuleFinderTest {
    @Test
    fun `Should find minimum rules 1`() {
        val game = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"

        assertEquals(
            mapOf(COLOUR.RED to 4, COLOUR.BLUE to 6, COLOUR.GREEN to 2),
            RuleFinder.findMinimumRulesForEachGame(GameBuilder.createGameFromLine(game))
        )
    }

    @Test
    fun `Should find minimum rules 2`() {
        val game = "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red"

        assertEquals(
            mapOf(COLOUR.RED to 14, COLOUR.BLUE to 15, COLOUR.GREEN to 3),
            RuleFinder.findMinimumRulesForEachGame(GameBuilder.createGameFromLine(game))
        )
    }
}