package advent.of.code.year2022.day17

import advent.of.code.util.Point
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ShapeTest {
    @ParameterizedTest
    @MethodSource("shapesData")
    fun part1Example1(shape: Shape, expectedPoints: Set<Point>) {
        Assertions.assertEquals(expectedPoints, shape.getPoints())
    }

    companion object {
        private val firstPoint = Point(2, 3)

        @JvmStatic
        fun shapesData(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    MinusShape(firstPoint), setOf(Point(2, 3), Point(3, 3), Point(4, 3), Point(5, 3))
                ),
                Arguments.of(
                    PlusShape(firstPoint), setOf(Point(2, 3), Point(2, 4), Point(1, 4), Point(3, 4), Point(2, 5))
                ),
                Arguments.of(
                    LShape(firstPoint), setOf(Point(2, 3), Point(3, 3), Point(4, 3), Point(4, 4), Point(4, 5))
                ),
                Arguments.of(
                    IShape(firstPoint), setOf(Point(2, 3), Point(2, 4), Point(2, 5), Point(2, 6))
                ),
                Arguments.of(
                    SquareShape(firstPoint), setOf(Point(2, 3), Point(3, 3), Point(2, 4), Point(3, 4))
                )
            )
        }
    }
}