package advent.of.code.year2022.day4

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SanitizerElfTest {

    @Test
    fun `Should not overlap`() {
        val elfs = Pair(SanitizerElf(2, 4), SanitizerElf(6, 8))
        Assertions.assertFalse(elfs.overlapFully())
    }
    @Test
    fun `Should  overlap`() {
        val elfs = Pair(SanitizerElf(2, 8), SanitizerElf(3, 7))
        Assertions.assertTrue(elfs.overlapFully())
    }

    @Test
    fun `Should  overlap includes lower range`() {
        val elfs = Pair(SanitizerElf(2, 8), SanitizerElf(2, 7))
        Assertions.assertTrue(elfs.overlapFully())
    }

    @Test
    fun `Should  overlap includes upper range`() {
        val elfs = Pair(SanitizerElf(3, 8), SanitizerElf(2, 8))
        Assertions.assertTrue(elfs.overlapFully())
    }

    @Test
    fun `Should  overlap same`() {
        val elfs = Pair(SanitizerElf(3, 8), SanitizerElf(3, 8))
        Assertions.assertTrue(elfs.overlapFully())
    }
}