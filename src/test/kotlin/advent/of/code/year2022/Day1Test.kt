package advent.of.code.year2022

import advent.of.code.year2022.day1.Elf
import advent.of.code.year2022.day1.ElfBuilder
import advent.of.code.year2022.day1.findElfWithMostWeight
import advent.of.code.year2022.day1.findNumberOfElfsWithMostWeight
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day1Test {
    @Test
    fun `Should calculate Total Elf weight correctly`() {
        assertEquals(2000, Elf(listOf(1000, 500, 300, 200)).calculateTotalCalories())
    }

    @Test
    fun `Should build elf correctly`() {
        val text =
            """
            1000
            2000
            3000
        """
        val result = ElfBuilder.buildElfs(text)
        assertEquals(1, result.size)
        assertEquals(Elf(listOf(1000, 2000, 3000)), result.first())
    }

    @Test
    fun `Should build MULTIPLE elf correctly`() {
        val text = ContentReader2022.readFileAsText(1)
        val result = ElfBuilder.buildElfs(text)
        assertEquals(5, result.size)
        assertEquals(Elf(listOf(1000, 2000, 3000)), result.first())
        assertEquals(Elf(listOf(10000)), result.last())
    }

    @Test
    fun `Should return elf with most calories`() {
        val elfs = listOf(Elf(listOf(2)), Elf(listOf(5)), Elf(listOf(1, 1, 1, 1)))
        val result = elfs.findElfWithMostWeight()
        assertEquals(5, result.calculateTotalCalories())
    }

    @Test
    fun `Should return top 3 with most calories`() {
        val text = ContentReader2022.readFileAsText(1)
        val result = ElfBuilder.buildElfs(text)
        val top3 = result.findNumberOfElfsWithMostWeight(3)
        assertEquals(45000, top3.sumOf { it.calculateTotalCalories() })
    }
}
