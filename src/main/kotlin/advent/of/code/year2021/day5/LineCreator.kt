package advent.of.code.year2021.day5

object LineCreator {
    private val regex = Regex("""(\d+),(\d+) -> (\d+),(\d+)""")

    fun createHorizontalOrVerticalLines(line: String, createDiagonal: Boolean): Set<Point> {
        return regex.matchEntire(line)
            ?.destructured
            ?.let { (x1, y1, x2, y2) ->
                createHorizontalOrVerticalLines(
                    x1.toInt(),
                    y1.toInt(),
                    x2.toInt(),
                    y2.toInt(),
                    createDiagonal
                )
            }!!
    }

    private fun createHorizontalOrVerticalLines(
        x1: Int,
        y1: Int,
        x2: Int,
        y2: Int,
        createDiagonal: Boolean
    ): Set<Point> {
        if (x1 != x2 && y1 != y2) {
            return if (createDiagonal) {
                createDiagonalLines(x1, y1, x2, y2)
            } else {
                emptySet()
            }
        }
        if (x1 == x2) {
            return (minOf(y1, y2)..maxOf(y1, y2))
                .map { Point(x1, it) }
                .toSet()
        }
        return (minOf(x1, x2)..maxOf(x1, x2))
            .map { Point(it, y1) }
            .toSet()
    }

    private fun createDiagonalLines(
        x1: Int,
        y1: Int,
        x2: Int,
        y2: Int
    ): Set<Point> {
        val points = mutableSetOf<Point>()
        var x = x1
        var y = y1
        if (x1 < x2 && y1 < y2) {
            while (x <= x2) {
                points.add(Point(x, y))
                x = x.inc()
                y = y.inc()
            }
            return points;
        }
        if (x1 < x2 && y1 > y2) {
            while (x <= x2) {
                points.add(Point(x, y))
                x = x.inc()
                y = y.dec()
            }
            return points;
        }
        if (x1 > x2 && y1 > y2) {
            while (x >= x2) {
                points.add(Point(x, y))
                x = x.dec()
                y = y.dec()
            }
            return points;
        }
        if (x1 > x2 && y1 < y2) {
            while (x >= x2) {
                points.add(Point(x, y))
                x = x.dec()
                y = y.inc()
            }
            return points;
        }
        throw IllegalAccessException("Should not reach this point")
    }
}