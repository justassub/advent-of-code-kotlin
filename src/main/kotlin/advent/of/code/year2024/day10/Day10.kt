package advent.of.code.year2024.day10

import advent.of.code.util.PointBuilder
import advent.of.code.util.TraversablePoint
import advent.of.code.util.aggregatePossibleWays
import java.util.*

fun main() {


    val traversablePoints = PointBuilder.createPointsFromFile(2024, 10)
        .let { PointBuilder.traversablePointsFromMapSimpleDirections(it) { c -> c.digitToInt() } }

    val startPositions = traversablePoints.filter { it.value == 0 }.toSet()

    val queue = LinkedList(startPositions)
    val startGoal = mutableSetOf<Pair<TraversablePoint<Int>, TraversablePoint<Int>>>()
    var possiblePaths = 0

    aggregatePossibleWays(
        queue,
        { current, neighbour -> neighbour.value - current.value == 1 },
        { it.value == 9 },
        listOf({ startGoal.add(it) }, { possiblePaths++ })
    )
    println(startGoal.size)
    println(possiblePaths)

}

