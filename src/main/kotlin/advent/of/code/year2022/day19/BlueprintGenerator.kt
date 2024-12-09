package advent.of.code.year2022.day19

import advent.of.code.year2022.day19.entity.Machine
import advent.of.code.year2022.day19.entity.Material

object BlueprintGenerator {
    //Blueprint 1: Each ore robot costs 3 ore. Each clay robot costs 4 ore. Each obsidian robot costs 4 ore and 18 clay. Each geode robot costs 3 ore and 8 obsidian.
    private val regex =
        Regex("""Blueprint (.*?): Each ore robot costs (.*?) ore. Each clay robot costs (.*?) ore. Each obsidian robot costs (.*?) ore and (.*?) clay. Each geode robot costs (.*?) ore and (.*?) obsidian.""".trimIndent())


    fun createBlueprints(line: String): Blueprint {
        return regex
            .matchEntire(line)
            ?.destructured
            ?.let { (_, amount1, amount2, amount3, amount4, amount5, amount6) ->
                Blueprint(
                    mapOf(
                        Machine.ORE_MACHINE to mapOf(Material.ORE to amount1.toInt()),
                        Machine.CLAY_MACHINE to mapOf(Material.ORE to amount2.toInt()),
                        Machine.OBSIDIAN_MACHINE to mapOf(
                            Material.ORE to amount3.toInt(),
                            Material.CLAY to amount4.toInt()
                        ),
                        Machine.GEODE_MACHINE to mapOf(
                            Material.ORE to amount5.toInt(),
                            Material.OBSIDIAN to amount6.toInt()
                        ),
                    )
                )
            }
            ?: throw IllegalArgumentException("Bad line: $line")

    }
}