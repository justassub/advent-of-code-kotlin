package advent.of.code.util

import java.util.*

fun <T> aggregatePossibleWays(
    starts: Queue<TraversablePoint<T>>,
    filter: (TraversablePoint<T>, TraversablePoint<T>) -> Boolean,
    goal: (TraversablePoint<T>) -> Boolean,
    aggregateResult: Collection<(Pair<TraversablePoint<T>, TraversablePoint<T>>) -> Unit>,
) {
    val queue = LinkedList(starts.map { it to it })
    while (queue.isNotEmpty()) {
        val (current, start) = queue.poll()

        if (goal(current)) {
            aggregateResult.forEach { it(start to current) }
        }
        current.findNeighbours { filter(current, it) }
            .forEach { queue.add(it to start) }
    }
}
