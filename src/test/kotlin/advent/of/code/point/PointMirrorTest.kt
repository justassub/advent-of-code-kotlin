package advent.of.code.point

import advent.of.code.util.Point
import advent.of.code.util.createPointsMirrorView
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class PointMirrorTest {

    @ParameterizedTest
    @MethodSource("pointMirrorProvider")
    fun `Should create proper points 1`(p1: Point, p2: Point, expected: List<Point>) {


        val result = createPointsMirrorView(p1, p2)
        Assertions.assertAll(
            { Assertions.assertEquals(expected.size, result.size) },
            { Assertions.assertTrue(result.containsAll(expected)) },

            )
    }


    private companion object {
        @JvmStatic
        private fun pointMirrorProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    Point(1, 0), Point(1, 3), listOf(Point(1, -3), Point(1, 6))
                ),
                Arguments.of(
                    Point(3, 2), Point(2, 2), listOf(Point(1, 2), Point(4, 2))
                ),
                Arguments.of(
                    Point(4, 3), Point(5, 5), listOf(Point(3, 1), Point(6, 7))
                ),
                Arguments.of(
                    Point(10, 17), Point(13, 12), listOf(Point(16, 7), Point(7, 22))
                )
            )
        }
    }
}
