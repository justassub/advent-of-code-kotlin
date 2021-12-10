package advent.of.code.year2021.day10

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals

class ChunkValidatorTest {
    @ParameterizedTest
    @CsvSource(
        "{()()()>,>",
        "<([]){()}[{}]),)",
        "{],]",
        "(((()))},}"
    )
    fun `Chunk Validator parses invalid chunks correctly`(chunk: Chunk, expected: Char) {
        assertEquals(Pair(ChunkType.CORRUPTED, listOf(expected)), ChunkValidator.validate(chunk))

    }

    @ParameterizedTest
    @CsvSource(
        "[({(<(())[]>[[{[]{<()<>>,}}]])})]",
        "[(()[<>])]({[<{<<[]>>(,)}>]})",
        "(((({<>}<{<{<>}{[]{[]{},}}>}>))))",
        "{<[[]]>}<{[{[{[]{()[[[],]]}}]}]}>",
        "<{([{{}}[<[[[<>{}]]]>[]],])}>",
        )
    fun `Chunk Validator parses incomplete chunks correctly`(chunk: Chunk, expected: String) {
        assertEquals(Pair(ChunkType.INCOMPLETE, expected.toList()), ChunkValidator.validate(chunk))
    }

}
