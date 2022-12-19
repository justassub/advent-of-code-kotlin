package advent.of.code.year2022.day19

import kotlin.math.max

data class Blueprint(
    val oreRobotPrice: MaterialPrice,
    val clayRobotPrice: MaterialPrice,
    val obsidianRobotPrice: MaterialPrice,
    val geodeRobotPrice: MaterialPrice
) {
    val maxOresNeeded =
        maxOf(oreRobotPrice.orePrice, clayRobotPrice.orePrice, obsidianRobotPrice.orePrice, geodeRobotPrice.orePrice)
    val maxClaysNeeded = max(obsidianRobotPrice.orePrice, geodeRobotPrice.orePrice)
    val maxObsidianNeeded = geodeRobotPrice.obsidianPrice
}
