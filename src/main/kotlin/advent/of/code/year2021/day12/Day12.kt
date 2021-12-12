package advent.of.code.year2021.day12

import advent.of.code.year2021.ContentReader

class Day12 {
    fun part1(data: List<String>): Long {
        val villages = VillageParser.parseVillages(data)
        val start = villages.first { it.villageType == VillageType.START }
        return countPossiblePaths(
            start, mutableMapOf(),
            alreadyVisitedSmallVillageTwice = false,
            allowMoreThanOnce = false
        )
    }

    fun part2(data: List<String>): Long {
        val villages = VillageParser.parseVillages(data)
        val start = villages.first { it.villageType == VillageType.START }
        return countPossiblePaths(
            start, mutableMapOf(),
            alreadyVisitedSmallVillageTwice = false,
            allowMoreThanOnce = true
        )
    }

    private fun countPossiblePaths(
        currentVillage: Village,
        visitedVillages: MutableMap<Village, Long>,
        alreadyVisitedSmallVillageTwice: Boolean,
        allowMoreThanOnce: Boolean
    ): Long {
        var alreadyVisitedSmallVillageTwiceValue = alreadyVisitedSmallVillageTwice
        if (currentVillage.villageType == VillageType.FINISH) {
            return 1
        }
        val amountOfTimesVisitedThisVillage = visitedVillages.getOrDefault(currentVillage, 0L)

        if (currentVillage.villageType == VillageType.START && amountOfTimesVisitedThisVillage > 0) {
            return 0
        }
        if (currentVillage.villageType == VillageType.SMALL && amountOfTimesVisitedThisVillage > 0) {
            if (!allowMoreThanOnce) {
                return 0
            }
            if (!alreadyVisitedSmallVillageTwiceValue) {
                alreadyVisitedSmallVillageTwiceValue = true
            } else {
                return 0
            }

        }

        val copyOfVisitedValues = visitedVillages.toMutableMap()

        copyOfVisitedValues.merge(currentVillage, 1L) { old, new ->
            old + new
        }

        return currentVillage.adjVillages.sumOf {
            countPossiblePaths(
                it,
                copyOfVisitedValues,
                alreadyVisitedSmallVillageTwiceValue,
                allowMoreThanOnce
            )
        }
    }
}


fun main() {
    val day12 = Day12()
    val data = ContentReader.readFileAsLines(12).filter { it.isNotEmpty() }
    println(day12.part1(data))
    println(day12.part2(data))

}
