package advent.of.code.year2022.day4

import advent.of.code.year2022.day4.SanitizerElf
import advent.of.code.year2022.day4.SanitizerMaker
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SanitizerMakerTest {

    @Test
    fun `Should create proper pair of sanitizers`() {
        val line = "2-4,6-8";

        val result = SanitizerMaker.createPairSanitizers(line)

        Assertions.assertEquals(SanitizerElf(2, 4), result.first)
        Assertions.assertEquals(SanitizerElf(6, 8), result.second)
    }
}