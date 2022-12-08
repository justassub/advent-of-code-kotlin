package advent.of.code.year2022.day8

import advent.of.code.year2022.ContentReader2022
import kotlin.system.measureTimeMillis

const val day = 8
fun main() {
    val timeInMillis = measureTimeMillis { solve() }

    println("Task 8 finished in $timeInMillis ms")
}

fun solve() {

    val trees = ContentReader2022.readFileAsLines(day)
        .let { ForestGenerator.generateForest(it) }
    val associatedTrees = ForestGenerator.associateEachTreeWithOtherThreesInLines(trees)


    val resultPart1 = solvePart1(associatedTrees)
    println(resultPart1)
    val resultPart2 = solvePart2(associatedTrees)
    println(resultPart2)
}

fun solvePart1(trees: Map<Tree, List<Tree>>): Int {
    return TreeValidator.getVisibleTrees(trees).count()
}

fun solvePart2(trees: Map<Tree, List<Tree>>): Int {
    return TreeScoreCalculator.calculateTreeWithScores(trees).maxOf { it.value }
}
