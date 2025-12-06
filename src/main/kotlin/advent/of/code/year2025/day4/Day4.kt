package advent.of.code.year2025.day4

import advent.of.code.util.Point
import advent.of.code.util.PointBuilder.createPointsFromFile
import advent.of.code.util.findNeighboursDiagonal

fun main() {
    val papers = createPointsFromFile(2025, 4)
        .filter { it.value == '@' }
        .toMutableMap()
    val removeOnce = removePaper(papers)
    println("removed papers first time: ${removeOnce.size}")
    removeOnce.forEach { papers.remove(it.key) }

    val totalRemovedPages = generateSequence {
        val toRemove = removePaper(papers)
        toRemove.forEach { papers.remove(it.key) }
        if (toRemove.isEmpty()) null else toRemove.size
    }.sum()

    print("total removed papers: ${totalRemovedPages + removeOnce.size}")

}

fun removePaper(papers: Map<Point, Char>): Map<Point, Char> {
    return papers
        .filter { it.key.findNeighboursDiagonal().filter { n -> papers[n] == '@' }.size < 4 }
}
