import advent.of.code.year2022.ContentReader2022
import advent.of.code.year2022.day7.UnixCommandGenerator
import advent.of.code.year2022.day7.UnixFile
import advent.of.code.year2022.day7.UnixFileStructureGenerator

import kotlin.system.measureTimeMillis

const val day = 7
fun main() {
    val timeInMillis = measureTimeMillis { solve() }

    println("Task 7 finished in $timeInMillis ms")
}

fun solve() {

    val unixStructure = ContentReader2022.readFileAsLines(day)
        .let { UnixCommandGenerator.generateUnixCommands(it) }
        .let { UnixFileStructureGenerator.generateUnixStructure(it) }


    val resultPart1 = solvePart1(unixStructure)
    println(resultPart1)
    val resultPart2 = solvePart2(unixStructure)
    println(resultPart2)
}

fun solvePart1(unixStructure: MutableMap<String, UnixFile>): Long {

    return unixStructure
        .values
        .filter { it.countTotalSize() < 100000 }
        .sumOf { it.countTotalSize() }

}

fun solvePart2(unixStructure: MutableMap<String, UnixFile>): Long {
    val available = 70_000_000
    val shouldHave = 30_000_000
    val freeSpaceNow = available - unixStructure.getValue("/").countTotalSize()
    val needToDelete = shouldHave - freeSpaceNow



    return unixStructure
        .map { Pair(it.key, it.value.countTotalSize()) }
        .filter { it.second >= needToDelete }
        .toList().minBy { it.second }
        .second
}