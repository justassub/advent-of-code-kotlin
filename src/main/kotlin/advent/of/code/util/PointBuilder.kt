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
}