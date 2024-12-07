package advent.of.code.year2024.util

import advent.of.code.util.builder.ListBuilder
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ListBuilderTest {
    val possibleValues = listOf("+", "*")

    @Test
    fun shouldCreateSimpleCases() {
        val input = "10 19"

        val result = ListBuilder.buildPossibleCombinationsFromString(input, " ", possibleValues, "")

        Assertions.assertAll(
            { Assertions.assertEquals(2, result.size) },
            { Assertions.assertTrue(result.contains("10+19")) },
            { Assertions.assertTrue(result.contains("10*19")) }
        )
    }

    @Test
    fun shouldCreateAdvancedCases() {
        val input = "81 40 27"

        val result = ListBuilder.buildPossibleCombinationsFromString(input, " ", possibleValues)

        Assertions.assertAll(
            { Assertions.assertEquals(4, result.size) },
            { Assertions.assertTrue(result.contains("81|+|40|+|27")) },
            { Assertions.assertTrue(result.contains("81|+|40|*|27")) },
            { Assertions.assertTrue(result.contains("81|*|40|+|27")) },
            { Assertions.assertTrue(result.contains("81|*|40|*|27")) }
        )
    }
}