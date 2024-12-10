package advent.of.code.util

data class TraversablePoint<T>(val value: T, val point: Point) {
    private lateinit var neighbours: Set<TraversablePoint<T>>

    fun findAllNeighbours(): Set<TraversablePoint<T>> {
        return neighbours
    }

    fun findNeighbours(predicate: (TraversablePoint<T>) -> Boolean): Collection<TraversablePoint<T>> {
        return neighbours.filter(predicate)
    }

    fun setNeighbours(neighbours: Set<TraversablePoint<T>>) {
        this.neighbours = neighbours
    }
}