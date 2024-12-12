package advent.of.code.util

import advent.of.code.ContentReader

object PointBuilder {


    fun createPointsWithValuesFromLines(lines: List<String>): Map<Point, Char> {
        return lines
            .flatMapIndexed { y, s -> s.mapIndexed { x, c -> Point(x, y) to c } }
            .toMap()
    }

    fun createPointsFromFile(year: Int, day: Int): Map<Point, Char> {
        return ContentReader.readFileAsLines(year, day)
            .let(::createPointsWithValuesFromLines)
    }

    fun <T> traversablePointsFromMapSimpleDirections(
        map: Map<Point, Char>,
        transformFunction: (Char) -> T,
    ): Set<TraversablePoint<T>> {
        return traversablePointsFromMapSimpleDirections(map, transformFunction) { _, _ -> true }
    }

    fun <T> traversablePointsFromMapSimpleDirections(
        map: Map<Point, Char>,
        transformFunction: (Char) -> T,
        filterNeighboursFunction: (TraversablePoint<T>, TraversablePoint<T>) -> Boolean
    ): Set<TraversablePoint<T>> {
        val points = map
            .map { (point, value) ->
                TraversablePoint(transformFunction(value), point)
            }

        val pointWithTraversablePoint = points.associateBy { it.point }

        points.forEach { p ->
            p.point.findNeighbours()
                .filter { map.containsKey(it) }
                .filter { filterNeighboursFunction(p, pointWithTraversablePoint[it]!!) }
                .map { pointWithTraversablePoint[it]!! }
                .let { p.setNeighbours(it.toSet()) }
        }
        return points.toSet()
    }
}