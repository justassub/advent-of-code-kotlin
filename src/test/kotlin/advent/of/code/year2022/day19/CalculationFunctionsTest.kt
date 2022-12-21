package advent.of.code.year2022.day19

import advent.of.code.year2022.day19.entity.Machine
import advent.of.code.year2022.day19.entity.Material
import advent.of.code.year2022.day19.service.collectResources
import advent.of.code.year2022.day19.service.plus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CalculationFunctionsTest {
    @Test
    fun `Should generate correct materials per day`() {
        val data = mapOf(
            Machine.ORE_MACHINE to 5,
            Machine.CLAY_MACHINE to 9,
            Machine.OBSIDIAN_MACHINE to 11,
            Machine.GEODE_MACHINE to 15
        )

        val result = data.collectResources()

        assertEquals(4, result.size)
        assertEquals(5, result[Material.ORE])
        assertEquals(9, result[Material.CLAY])
        assertEquals(11, result[Material.OBSIDIAN])
        assertEquals(15, result[Material.GEODE])
    }

    @Test
    fun `Should sum two material maps`() {
        val d1 = mapOf(Material.ORE to 2, Material.CLAY to 5)
        val d2 = mapOf(Material.ORE to 4, Material.GEODE to 7)

        val result = d1 + (d2)
        assertEquals(6, result.get(Material.ORE))
        assertEquals(5, result.get(Material.CLAY))
        assertEquals(0, result.get(Material.OBSIDIAN))
        assertEquals(7, result.get(Material.GEODE))
    }

}