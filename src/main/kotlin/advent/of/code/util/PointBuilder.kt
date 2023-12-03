package advent.of.code.util

object PointBuilder {


    fun createPointsWithValuesFromLines(lines: List<String>): Map<Point, Char> {
        return lines
            .flatMapIndexed { y, s -> s.mapIndexed { x, c -> Point(x, y) to c } }
            .toMap()
    }
}