package advent.of.code.year2023.day5

import advent.of.code.ContentReader
import advent.of.code.year2022.day8.takeWhileInclusive


fun main() {
    val data = provideData()
    "1972667147 405592018 1450194064 27782252 348350443 61862174 3911195009 181169206 626861593 138786487 2886966111 275299008 825403564 478003391 514585599 6102091 2526020300 15491453 3211013652 546191739"
        .split(" ")
        .map { it.toLong() }
        .map { calculateAllResources(it, data, SeedLevel.LOCATION) }
        .minOf { it[SeedLevel.LOCATION]!! }
        .run { println(this) }


    println("")
}

fun calculateAllResources(startData: Long, data: List<ProcessData>, target: SeedLevel)
        : Map<SeedLevel, Long> {
    return getProcessSequence()
        .takeWhileInclusive { it.second != target }
        .runningFold(SeedLevel.SEED to startData) { acc, pair -> findNextResource(data, acc, pair.second) }
        .toMap()

}

fun findNextResource(data: List<ProcessData>, source: Pair<SeedLevel, Long>, target: SeedLevel): Pair<SeedLevel, Long> {
    val filters = data
        .filter { it.seedFrom == source.first && it.seedTo == target }
        .filter { it.processStartInc <= source.second && it.processEndInc >= source.second }

    if (filters.size > 2) {
        throw IllegalArgumentException("Should not have 2 available filters")
    }
    val changeBy = filters
        .singleOrNull()
        ?.changeBy ?: 0
    return target to source.second + changeBy
}


private fun getProcessSequence() = listOf(
    SeedLevel.SEED to SeedLevel.SOIL,
    SeedLevel.SOIL to SeedLevel.FERTILIZER,
    SeedLevel.FERTILIZER to SeedLevel.WATER,
    SeedLevel.WATER to SeedLevel.LIGHT,
    SeedLevel.LIGHT to SeedLevel.TEMPERATURE,
    SeedLevel.TEMPERATURE to SeedLevel.HUMIDITY,
    SeedLevel.HUMIDITY to SeedLevel.LOCATION
)

private fun provideData(): List<ProcessData> {
    return ContentReader.readFileAsText(2023, 5)
        .split("\n\n")
        .flatMap { ProcessDataBuilder.createMultipleProcessData(it.lines()) }
}