package advent.of.code.year2021.day5

object LineCreator {
    private val regex = Regex("""(\d+),(\d+) -> (\d+),(\d+)""")
    fun createHorizontalOrVerticalLines(line: String): List<Point> {
        // 0,9 -> 5,9

        return regex.matchEntire(line)
            ?.destructured
            ?.let { (x1, y1, x2, y2) ->
                createHorizontalOrVerticalLines(
                    x1.toInt(),
                    y1.toInt(),
                    x2.toInt(),
                    y2.toInt()
                )
            }!!
    }

    private fun createHorizontalOrVerticalLines(x1: Int, y1: Int, x2: Int, y2: Int): List<Point> {
        if (x1 != x2 && y1 != y2) {
            return emptyList()
        }
        if (x1 == x2) {
            return (minOf(y1, y2)..maxOf(y1, y2))
                .map { Point(x1, it) }
        }
        return (minOf(x1, x2)..maxOf(x1, x2))
            .map { Point(it, y1) }
    }
}