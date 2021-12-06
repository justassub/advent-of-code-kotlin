package advent.of.code.year2021.day6

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day6Test {
    private val day6 = Day6()
    private val data = "3,4,3,1,2".split(",").map { it.toInt() }

    @ParameterizedTest
    @MethodSource("fishLife")
    fun part1Example1(days: Int, expected: Int) {
        Assertions.assertEquals(expected, day6.task1(data, days))
    }

    companion object {
        @JvmStatic
        fun fishLife(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    1, 5
                ),
                Arguments.of(
                    2, 6
                ),
                Arguments.of(
                    3, 7
                ),
                Arguments.of(
                    4, 9
                ),
                Arguments.of(
                    5, 10
                ),
                Arguments.of(
                    18, 26
                ),
                Arguments.of(
                    5934, 80
                ),
                Arguments.of(
                    26984457539, 256
                )
            )
        }
    }

}