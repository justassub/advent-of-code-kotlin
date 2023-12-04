package advent.of.code.year2022.day19

import advent.of.code.year2022.day19.entity.Machine
import advent.of.code.year2022.day19.entity.Material

data class Blueprint(
    val prices: Map<Machine, Map<Material, Int>>
) {
    val maxOresNeeded = prices.values.maxOf { it.getOrDefault(Material.ORE, 0) }
    val maxClaysNeeded = prices.values.maxOf { it.getOrDefault(Material.CLAY, 0) }
    val maxObsidianNeeded = prices.values.maxOf { it.getOrDefault(Material.OBSIDIAN, 0) }

    fun maximumMachinesNeed(machine: Machine): Int {
        return when (machine) {
            Machine.ORE_MACHINE -> maxOresNeeded
            Machine.CLAY_MACHINE -> maxClaysNeeded
            Machine.OBSIDIAN_MACHINE -> maxObsidianNeeded
            Machine.GEODE_MACHINE -> Int.MAX_VALUE
        }
    }

    fun hasEnoughResourcesToPurchaseMachine(machine: Machine, resources: Map<Material, Int>): Boolean {
        return prices[machine]
            ?.let { return it.all { r -> r.value <= resources.getOrDefault(r.key, 0) } }
            ?: false
    }

}
