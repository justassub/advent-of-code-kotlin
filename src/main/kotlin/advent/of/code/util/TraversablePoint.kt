package advent.of.code.util

data class TraversablePoint<T>(val value: T, val point: Point) {
    private lateinit var neighbours: Set<TraversablePoint<T>>

    fun findAllNeighbours(): Set<TraversablePoint<T>> {
        return neighbours
    }

    fun findNextNeighbourByDirection(
        direction: Direction,
        predicate: (TraversablePoint<T>) -> Boolean = { true }
    ): TraversablePoint<T>? {
        return neighbours.firstOrNull { it.point == this.point.recreateByDirectionReverse(direction) && predicate(it) }
    }

    fun findNeighbours(predicate: (TraversablePoint<T>) -> Boolean): Collection<TraversablePoint<T>> {
        return neighbours.filter(predicate)
    }

    fun setNeighbours(neighbours: Set<TraversablePoint<T>>) {
        this.neighbours = neighbours
    }

    fun collectAllJoiningPoints(alreadyCollected: MutableSet<TraversablePoint<T>> = mutableSetOf()) {
        return findAllNeighbours()
            .filter { alreadyCollected.add(it) }
            .forEach { it.collectAllJoiningPoints(alreadyCollected) }
    }
}