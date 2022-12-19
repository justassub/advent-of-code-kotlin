package advent.of.code.year2022.day19

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
                    oreRobotPrice = MaterialPrice(amount1.toInt(), 0, 0),
                    clayRobotPrice = MaterialPrice(amount2.toInt(), 0, 0),
                    obsidianRobotPrice = MaterialPrice(amount3.toInt(), amount4.toInt(), 0),
                    geodeRobotPrice = MaterialPrice(amount5.toInt(), 0, amount6.toInt())
                )
            }
            ?: throw IllegalArgumentException("Bad line: $line")

    }
}
