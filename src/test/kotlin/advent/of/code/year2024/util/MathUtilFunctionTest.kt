package advent.of.code.year2024.util

import advent.of.code.util.math.MathUtil
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals

class MathUtilFunctionTest {


    @ParameterizedTest
    @CsvSource(
        "81 * 40 + 27,3267",
        "81 + 40 * 27,3267",
        "11 + 6 * 16 + 20,292",
    )
    fun `should properly solve more difficult math no priority`(expression: String, result: Long) {
        assertEquals(
            result,
            MathUtil.solveEvaluationWithoutParenthesesNoPriority(expression),
        )
    }
}