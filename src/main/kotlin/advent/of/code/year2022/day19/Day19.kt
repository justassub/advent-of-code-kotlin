package advent.of.code.year2022.day19

import advent.of.code.year2022.day19.BlueprintGenerator.createBlueprints
import advent.of.code.year2022.day19.entity.Machine
import advent.of.code.year2022.day19.entity.Material
import kotlin.system.measureTimeMillis

const val day = 19


fun main() {
    val timeInMillis = measureTimeMillis { solve() }

    println("Task 19 finished in $timeInMillis ms")
}

fun solve() {


    println(solvePart1())
}

fun solvePart1(): Int {
    val bluePrint = createBlueprints(
        "Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian."
    )

    return BluePrintCalculator(bluePrint).calculateMaxGeode(
        24,
        Material.values().associateWith { 0 },
        mapOf(
            Machine.ORE_MACHINE to 1,
            Machine.CLAY_MACHINE to 0,
            Machine.OBSIDIAN_MACHINE to 0,
            Machine.GEODE_MACHINE to 0
        )
    )
}
