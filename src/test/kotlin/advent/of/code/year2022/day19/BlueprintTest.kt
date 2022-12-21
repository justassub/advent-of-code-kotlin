package advent.of.code.year2022.day19

import advent.of.code.year2022.day19.entity.Machine
import advent.of.code.year2022.day19.entity.Material
import advent.of.code.year2022.day19.service.purchaseOrNull
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.test.*

class BlueprintTest {
    private val line =
        "Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 3 ore and 7 obsidian."

    val blueprint = BlueprintGenerator.createBlueprints(line)

    @Test
    fun `Should return correct mapped values`() {


        Assertions.assertEquals(14, blueprint.maximumMachinesNeed(Machine.ORE_MACHINE))
        Assertions.assertEquals(4, blueprint.maximumMachinesNeed(Machine.CLAY_MACHINE))
        Assertions.assertEquals(7, blueprint.maximumMachinesNeed(Machine.OBSIDIAN_MACHINE))

        Assertions.assertEquals(mapOf(Material.ORE to 4), blueprint.prices[Machine.ORE_MACHINE])
        Assertions.assertEquals(mapOf(Material.ORE to 2), blueprint.prices[Machine.CLAY_MACHINE])
        Assertions.assertEquals(
            mapOf(Material.ORE to 3, Material.CLAY to 14),
            blueprint.prices[Machine.OBSIDIAN_MACHINE]
        )
        Assertions.assertEquals(
            mapOf(Material.ORE to 3, Material.OBSIDIAN to 7),
            blueprint.prices[Machine.GEODE_MACHINE]
        )
    }

    @Test
    fun `Should not be able to buy Obsidian`() {
        val ableToBuy = blueprint.hasEnoughResourcesToPurchaseMachine(
            Machine.OBSIDIAN_MACHINE,
            mapOf(Material.ORE to 2, Material.CLAY to 15, Material.GEODE to 9)
        )
        assertFalse(ableToBuy)
    }

    @Test
    fun `Should  be able to buy Obsidian`() {
        val ableToBuy = blueprint.hasEnoughResourcesToPurchaseMachine(
            Machine.OBSIDIAN_MACHINE,
            mapOf(Material.ORE to 3, Material.CLAY to 15, Material.GEODE to 9)
        )
        assertTrue(ableToBuy)
    }

    @Test
    fun `Should not purchase too many Machines `() {
        val purchaseResult = Machine.ORE_MACHINE.purchaseOrNull(
            blueprint,
            mapOf(Material.ORE to 13, Material.CLAY to 15, Material.GEODE to 9),
            mapOf(Machine.ORE_MACHINE to 14)
        )
        assertNull(purchaseResult)
    }

    @Test
    fun `Should not purchase if not enough Resources`() {
        val purchaseResult = Machine.ORE_MACHINE.purchaseOrNull(
            blueprint,
            mapOf(Material.ORE to 3, Material.CLAY to 15, Material.GEODE to 9),
            mapOf(Machine.ORE_MACHINE to 2)
        )
        assertNull(purchaseResult)
    }

    @Test
    fun `Should purchase correctly`() {
        val purchaseResult = Machine.OBSIDIAN_MACHINE.purchaseOrNull(
            blueprint,
            mapOf(Material.ORE to 3, Material.CLAY to 15, Material.GEODE to 9),
            mapOf(Machine.ORE_MACHINE to 2, Machine.OBSIDIAN_MACHINE to 0)
        )

        assertNotNull(purchaseResult)
        assertEquals(
            mapOf(Machine.ORE_MACHINE to 2, Machine.OBSIDIAN_MACHINE to 1),
            purchaseResult.first
        )
        assertEquals(
            mapOf(Material.ORE to 0, Material.CLAY to 1, Material.OBSIDIAN to 0, Material.GEODE to 9),
            purchaseResult.second
        )
    }
}