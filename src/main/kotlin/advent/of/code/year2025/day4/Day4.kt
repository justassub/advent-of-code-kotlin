package advent.of.code.year2025.day4

import advent.of.code.util.Point
import advent.of.code.util.PointBuilder.createPointsFromFile
import advent.of.code.util.findNeighboursDiagonal

fun main() {
    val papers = createPointsFromFile(2025, 4)
        .filter { it.value == '@' }

    val remove = removePaper(papers)
    println("removed papers: ${remove.size}")

}

fun removePaper(papers: Map<Point, Char>): Map<Point, Char> {
    return papers
        .filter { it.key.findNeighboursDiagonal().filter { n -> papers[n] == '@' }.size < 4 }
}
