package advent.of.code.year2022.day19

import advent.of.code.year2022.day19.BlueprintGenerator.createBlueprints
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

    return BluePrintCalculator().calculateMaxGeode(
        24, Storage(
            bluePrint,
            MaterialStorage(0, 0, 0,0),
            MachineStorage(1, 0, 0, 0)
        )
    )
}
