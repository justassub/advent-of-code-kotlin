package advent.of.code.year2022.day3

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class RucksackMakerTest {

    @Test
    fun `Should make correct Rucksack`() {
        val rucksack = RucksackMaker.makeRucksackBySplitInHalf("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL")
        Assertions.assertEquals(Rucksack(listOf("jqHRNqRjqzjGDLGL", "rsFMfFZSrLrFZsSL")), rucksack)
    }
}
