package advent.of.code.year2024.day6

import advent.of.code.util.PointBuilder
import advent.of.code.util.action.Labyrint
import advent.of.code.util.printResult

fun main() {
    val points = PointBuilder.createPointsFromFile(2024, 6)
    val obstacles = points.filter { it.value == '#' }.keys

    val player = points.filter { it.value == '^' }.firstNotNullOf { it }.key

    Labyrint(player, obstacles)
        .findVisitedLocationsToExit()
        .count()
        .printResult()

    val emptySpaces = points.filter { it.value == '.' }.keys

    emptySpaces.count {
        Labyrint(player, obstacles + it).isInfiniteLoop()
    }.printResult()

}


