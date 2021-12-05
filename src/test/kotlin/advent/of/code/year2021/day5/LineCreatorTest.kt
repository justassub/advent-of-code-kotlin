package advent.of.code.year2021.day5

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class LineCreatorTest {

    @ParameterizedTest
    @MethodSource("linesProvider")
    fun calculateLinesCreation(line: String, result: List<Point>) {
        Assertions.assertEquals(result, LineCreator.createHorizontalOrVerticalLines(line, false))
    }

    companion object {
        @JvmStatic
        fun linesProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    "1,1 -> 2,4", listOf<Point>()
                ),
                Arguments.of(
                    "1,1 -> 1,3", listOf(Point(1, 1), Point(1, 2), Point(1, 3))
                ),
                Arguments.of(
                    "9,7 -> 7,7", listOf(Point(7, 7), Point(8, 7), Point(9, 7))
                ),
            )
        }
    }
}