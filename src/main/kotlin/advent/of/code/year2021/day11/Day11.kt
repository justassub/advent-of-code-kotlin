package advent.of.code.year2021.day11

import advent.of.code.year2021.ContentReader

class Day11 {
    fun part1(lines: List<String>, days: Int = 100): Long {
        val octopuses = OctupusBuilder.buildOctupuses(lines)

        return (0L until days)
            .fold(0) { acc, _ -> acc + startStage(octopuses) }
    }

    fun part2(lines: List<String>): Long {
        val octopuses = OctupusBuilder.buildOctupuses(lines)

        var day = 0L
        do {
            startStage(octopuses)
            day = day.inc()
        } while (!areOctupusesSynchronized(octopuses))
        return day
    }

    private fun startStage(octupusCache: Map<Long, Octupus>): Long {
        val stage = Stage(octupusCache)
        stage.start()
        return stage.flashedOctupus.size.toLong()
    }

    private fun areOctupusesSynchronized(octupusCache: Map<Long, Octupus>): Boolean {
        val energyLevelShouldBe = octupusCache.firstNotNullOf { it.value }.energyLevel
        return octupusCache.values.all { it.energyLevel == energyLevelShouldBe }
    }
}


fun main() {
    val day11 = Day11()
    val data = ContentReader.readFileAsLines(11)
        .filter { it.isNotEmpty() }

    println(day11.part1(data))
    println(day11.part2(data))

}
