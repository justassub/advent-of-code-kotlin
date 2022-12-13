package advent.of.code.year2022.day13

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class PacketComparatorTest {

    @ParameterizedTest
    @MethodSource("data")
    fun `Should return comparedObjects`(packet1: Packet, packet2: Packet, expectedResult: Boolean) {
        Assertions.assertEquals(expectedResult, PacketComparator.compare(packet1, packet2))
    }

    companion object {
        @JvmStatic
        fun data(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    listOf(7, 7, 7), listOf(7, 7), false
                ),
                Arguments.of(
                    listOf(7, 7), listOf(7, 7, 7), true
                ),
                Arguments.of(
                    listOf(7), listOf(5, 9), false
                ),
                Arguments.of(
                    listOf(7), listOf(5, 9), false
                ),
                Arguments.of(
                    emptyList<Any>(), emptyList<Any>(), true
                ),
                Arguments.of(
                    emptyList<Any>(), listOf(3), true
                ),
                Arguments.of(
                    listOf(listOf(emptyList<Any>())), listOf(listOf<Any>()), false
                ),
                Arguments.of(
                    listOf(listOf(1), listOf(2, 3, 4)), listOf(listOf(1), 4), true
                ),
                Arguments.of(
                    listOf(9), listOf(listOf(8, 7, 6)), false
                ),
                Arguments.of(
                    listOf(listOf(4, 4), 4, 4), listOf(listOf(4, 4), 4, 4, 4), true
                ),
                Arguments.of(
                    listOf(1, listOf(2, listOf(3, listOf(4, listOf(5, 6, 7)))), 8, 9),
                    listOf(1, listOf(2, listOf(3, listOf(4, listOf(5, 6, 0)))), 8, 9),
                    false
                ),

                )
        }
    }
}
