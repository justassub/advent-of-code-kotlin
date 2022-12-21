package advent.of.code.year2022.day19

import advent.of.code.year2022.day19.entity.Machine
import advent.of.code.year2022.day19.entity.Material
import advent.of.code.year2022.day19.service.collectDailyResources
import advent.of.code.year2022.day19.service.purchaseOrNull

class BluePrintCalculator(private val blueprint: Blueprint) {

    fun calculateMaxGeode(
        min: Int,
        materials: Map<Material, Int>,
        machines: Map<Machine, Int>
    ): Int {
        if (min == 0) {
            return materials.getOrDefault(Material.GEODE, 0)
        }
        val tryToPurchaseGeoMachine = Machine.GEODE_MACHINE.purchaseOrNull(blueprint, materials, machines)
        return if (tryToPurchaseGeoMachine != null) {
            calculateMaxGeode(min - 1, tryToPurchaseGeoMachine.second, tryToPurchaseGeoMachine.first)
        } else {
            setOfNotNull(
                Pair(machines, materials),
                Machine.ORE_MACHINE.purchaseOrNull(blueprint, materials, machines),
                Machine.CLAY_MACHINE.purchaseOrNull(blueprint, materials, machines),
                Machine.OBSIDIAN_MACHINE.purchaseOrNull(blueprint, materials, machines)
            )
                .maxOf { calculateMaxGeode(min - 1, it.second.collectDailyResources(machines), it.first) }
        }

    }
}
