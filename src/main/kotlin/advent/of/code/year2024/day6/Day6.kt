package advent.of.code.year2024.day6

import advent.of.code.util.PointBuilder
import advent.of.code.util.action.Labyrint
import advent.of.code.util.printResult
import kotlin.time.measureTime

fun main() {
    measureTime {
        val points = PointBuilder.createPointsFromFile(2024, 6)
        val obstacles = points.filter { it.value == '#' }.keys

        val player = points.filter { it.value == '^' }.firstNotNullOf { it }.key

        val findVisitedLocationsToExit = Labyrint(player, obstacles)
            .findVisitedLocationsToExit()

        findVisitedLocationsToExit
            .count()
            .printResult()

        findVisitedLocationsToExit.parallelStream().filter {
            Labyrint(player, obstacles + it).isInfiniteLoop()
        }.count().printResult()
    }.printResult()

}


