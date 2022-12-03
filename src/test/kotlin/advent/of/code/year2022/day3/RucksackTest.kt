package advent.of.code.year2022.day3

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class RucksackTest {

    @Test
    fun `Should return empty list if no elements`() {
        Assertions.assertTrue(Rucksack(emptyList()).findCommonElement().isEmpty())
    }

    @Test
    fun `Should return empty list if no common elements`() {
        Assertions.assertTrue(Rucksack(listOf("A", "B")).findCommonElement().isEmpty())
    }

    @Test
    fun `Should return  list  of common elements`() {
        val commonElements = Rucksack(listOf("jqHRNqRjpqzjGDLGL", "rsFMpfFZSrLrFZsSL")).findCommonElement()
        Assertions.assertTrue(commonElements.size == 2)
        Assertions.assertTrue(commonElements.containsAll(setOf('L', 'p')))
    }

}
