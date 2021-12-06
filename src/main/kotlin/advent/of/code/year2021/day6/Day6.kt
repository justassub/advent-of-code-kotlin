package advent.of.code.year2021.day6

import advent.of.code.year2021.ContentReader

class Day6 {
    fun task1(fishesWithInitialSpawnDays: List<Int>, days: Int): Long {
        val initialFish = fishesWithInitialSpawnDays
            .map { Fish(it) }
        spendTime(initialFish, days)
        return initialFish.sumOf { it.countYourselfAndChildren() }
    }

    private fun spendTime(fish: List<Fish>, days: Int) {
        repeat(times = days, action = { _ -> fish.forEach { it.spendDay() } })
    }

}

fun main() {
    val initialFish = ContentReader.readFileAsText(6).split(",")
        .map { it.toInt() }

    val day6 = Day6()

    println(day6.task1(initialFish, 80))
}