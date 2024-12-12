package advent.of.code.util.shape

import advent.of.code.util.TraversablePoint
import advent.of.code.util.findNeighbours

data class Shape2D<T>(val points: Set<TraversablePoint<T>>) {
    fun calculatePerimeter(): Int {
        val pointSet = points.map { it.point }.toSet()
        return points
            .flatMap {
                it.point
                    .findNeighbours()
                    .filter { !pointSet.contains(it) }
            }
            .count()
    }

}
