package advent.of.code.year2022.day8

import advent.of.code.year2021.Point

object ForestGenerator {

    fun generateForest(lines: List<String>): List<Tree> {
        return (lines.indices)
            .flatMap { generateTreeRow(it, lines[it]) }
    }

    fun associateEachTreeWithOtherThreesInLines(forest: List<Tree>): Map<Tree, List<Tree>> {
        val mapTrees = forest.associateBy { Point(it.x, it.y) }
        return forest.associateWith { associateEachTreeWithOthersInLines(it, mapTrees) }
    }

    private fun associateEachTreeWithOthersInLines(tree: Tree, forest: Map<Point, Tree>): List<Tree> {
        val maxY = forest.maxOf { it.key.y }
        val maxX = forest.maxOf { it.key.y }
        val xPoints = (0..maxX).map { Point(it, tree.y) }
        val yPoints = (0..maxY).map { Point(tree.x, it) }

        return (xPoints + yPoints)
            .filterNot { it.x == tree.x && it.y == tree.y }
            .map { forest.getValue(it) }
    }


    private fun generateTreeRow(column: Int, text: String) =
        text.withIndex().map { Tree(it.index, column, it.value.digitToInt()) }


}
