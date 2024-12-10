package advent.of.code.year2024.day10

import advent.of.code.util.PointBuilder
import advent.of.code.util.TraversablePoint
import advent.of.code.util.printResult
import java.util.*

fun main() {


    val traversablePoints = PointBuilder.createPointsFromFile(2024, 10)
        .let { PointBuilder.traversablePointsFromMapSimpleDirections(it) { c -> c.digitToInt() } }

    val startPositions = traversablePoints.filter { it.value == 0 }.toSet()

    val queue = LinkedList(startPositions)

    calculatePossibleWays(queue,
        { current, neighbour -> neighbour.value - current.value == 1 },
        { it.value == 9 }
    ).printResult()

}

fun <T> calculatePossibleWays(
    starts: Queue<TraversablePoint<T>>,
    filter: (TraversablePoint<T>, TraversablePoint<T>) -> Boolean,
    goal: (TraversablePoint<T>) -> Boolean,
): Int {
    val startGoal = mutableSetOf<Pair<TraversablePoint<T>, TraversablePoint<T>>>()
    val queue = LinkedList(starts.map { it to it })
    while (queue.isNotEmpty()) {
        val (current, start) = queue.poll()

        if (goal(current)) {
            startGoal.add(current to start)
        }
        current.findNeighbours { filter(current, it) }
            .forEach { queue.add(it to start) }
    }
    return startGoal.size
}
