package advent.of.code.year2022.day19.service

import advent.of.code.year2022.day19.Blueprint
import advent.of.code.year2022.day19.entity.Machine
import advent.of.code.year2022.day19.entity.Material

fun Map<Machine, Int>.collectResources(): Map<Material, Int> {
    return mapOf(
        Material.ORE to getOrDefault(Machine.ORE_MACHINE, 0),
        Material.CLAY to getOrDefault(Machine.CLAY_MACHINE, 0),
        Material.OBSIDIAN to getOrDefault(Machine.OBSIDIAN_MACHINE, 0),
        Material.GEODE to getOrDefault(Machine.GEODE_MACHINE, 0)
    )
}

fun Map<Material, Int>.collectDailyResources(machines: Map<Machine, Int>): Map<Material, Int> {
    return this + machines.collectResources()
}

operator fun Map<Material, Int>.plus(other: Map<Material, Int>): Map<Material, Int> {
    return Material.values().associateWith {
        getOrDefault(it, 0) + other.getOrDefault(it, 0)
    }
}

operator fun Map<Material, Int>.minus(other: Map<Material, Int>): Map<Material, Int> {
    return Material.values().associateWith {
        getOrDefault(it, 0) - other.getOrDefault(it, 0)
    }
}


fun Machine.purchaseOrNull(
    blueprint: Blueprint,
    materials: Map<Material, Int>,
    machines: Map<Machine, Int>
): Pair<Map<Machine, Int>, Map<Material, Int>>? {
    return when {
        blueprint.maximumMachinesNeed(this) <= machines.getOrDefault(this, 0) -> null
        !blueprint.hasEnoughResourcesToPurchaseMachine(this, materials) -> null
        else -> Pair(machines.plusMachine(mapOf(this to 1)), materials - blueprint.prices.getValue(this))
    }
}

fun Map<Machine, Int>.plusMachine(other: Map<Machine, Int>): Map<Machine, Int> {
    return map { it.key to it.value + other.getOrDefault(it.key, 0) }.toMap()
}

