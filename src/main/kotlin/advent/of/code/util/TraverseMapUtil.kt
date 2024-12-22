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

fun <T> findShortestPath(
    start: TraversablePoint<T>,
    goal: (TraversablePoint<T>),
    filterNeighbours: (Collection<TraversablePoint<T>>) -> (Collection<TraversablePoint<T>>),
    addScore: Int = 1,
): Int {
    var result = Int.MAX_VALUE
    val visited = mutableMapOf(start to 0)
    val queue = LinkedList(listOf(start to 0))
    while (queue.isNotEmpty()) {
        val (current, score) = queue.poll()

        if (goal == current) {
            result = minOf(result, score)
        }
        filterNeighbours(current.findAllNeighbours())
            .map { it to score + addScore }
            .filter { (neighbour, newScore) ->
                visited.getOrDefault(neighbour, Int.MAX_VALUE) > newScore
            }
            .forEach {
                visited[it.first] = it.second
                queue.add(it)
            }
    }
    return result
}

fun findAllPaths(
    start: MovingData,
    finish: TraversablePoint<Char>,
    filterNeighbours: (MovingData) -> Collection<TraversablePoint<Char>>,
    mapFunction: (MovingData, TraversablePoint<Char>) -> MovingData,
    additionFilters: (MovingData) -> Boolean
): Long {
    var allPaths = 0L
    val stack = ArrayDeque<Pair<MovingData, MutableList<TraversablePoint<Char>>>>()
    stack.push(start to mutableListOf())

    while (stack.isNotEmpty()) {
        val (current, path) = stack.pop()
        path.add(current.point)

        if (current.point == finish) {
            allPaths++
        } else {
            filterNeighbours(current)
                .filter { it !in path }
                .map { mapFunction(current, it) }
                .filter { additionFilters(it) }
                .forEach { stack.push(it to path.toMutableList()) }
        }
    }

    return allPaths
}

data class MovingData(
    val point: TraversablePoint<Char>,
    val seconds: Int,
    val allowsToEnter: Int
)