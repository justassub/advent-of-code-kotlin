package advent.of.code.year2022.day13

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class PacketGeneratorTest {

    @ParameterizedTest
    @MethodSource("data")
    fun `Should create Proper Packets`(packetLine: String, expectedResult: Packet) {
        Assertions.assertEquals(expectedResult, PacketGenerator.parseList(packetLine))
    }

    companion object {
        @JvmStatic
        fun data(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    "[7,7,7,7]", listOf(7, 7, 7, 7),
                ),
                Arguments.of(
                    "[7,7]", listOf(7, 7)
                ),

                Arguments.of(
                    "[7]", listOf(7)
                ),
                Arguments.of(
                    "[5,9]", listOf(5, 9)
                ),
                Arguments.of(
                    "[]", emptyList<Any>()
                ),
                Arguments.of(
                    "[[[]]]", listOf(listOf(emptyList<Any>())),
                ),
                Arguments.of(
                    "[[]]", listOf(listOf<Any>())
                ),
                Arguments.of(
                    "[[1],[2,3,4]]", listOf(listOf(1), listOf(2, 3, 4))
                ),
                Arguments.of(
                    "[[2,3,4]]", listOf(listOf(2, 3, 4))
                ),
                Arguments.of(
                    "[[4,4],4,4,4]", listOf(listOf(4, 4), 4, 4, 4)
                ),
                Arguments.of(
                    "[1,[2,[3,[4,[5,6,0]]]],8,9]",
                    listOf(1, listOf(2, listOf(3, listOf(4, listOf(5, 6, 0)))), 8, 9)
                ),
                )
        }
    }
}
