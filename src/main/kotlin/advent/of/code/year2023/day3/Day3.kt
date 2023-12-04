import advent.of.code.ContentReader
import advent.of.code.util.Point
import advent.of.code.util.PointBuilder
import advent.of.code.util.findNeighboursDiagonal

fun main() {
    val data = ContentReader.readFileAsLines(2023, 3)
        .let { PointBuilder.createPointsWithValuesFromLines(it) }

    val groupedNumbers = createGroupedNumbers(data)
        .filterNot { it.value.isEmpty() }


    groupedNumbers
        .filter { it.isPartNumber(data) }
        .sumOf { g ->
            g.value.map { it.second.digitToInt() }.joinToString("").toInt()
        }
        .run {
            println(this)
        }

}


class NumberGroup(val value: List<Pair<Point, Char>>) {
    fun isPartNumber(schema: Map<Point, Char>): Boolean {
        return value.flatMap { it.first.findNeighboursDiagonal() }
            .mapNotNull { schema[it] }
            .filterNot { it.isDigit() }
            .any { it != '.' }

    }
}

private fun createGroupedNumbers(data: Map<Point, Char>): List<NumberGroup> {
    val groups = mutableListOf<NumberGroup>()
    val singleGroup = mutableListOf<Pair<Point, Char>>()

    val maxX = data.keys.maxOf { it.x }
    val maxY = data.keys.maxOf { it.y }

    (0..maxY)
        .forEach { y ->
            if(singleGroup.isNotEmpty()){
                groups.add(NumberGroup(singleGroup.toList()))
                singleGroup.clear()
            }
            singleGroup.clear()
            (0..maxX).forEach { x ->
                val point = Point(x, y)
                val value = data[point]!!
                if (value.isDigit()) {
                    singleGroup.add(point to value)
                } else {
                    groups.add(NumberGroup(singleGroup.toList()))
                    singleGroup.clear()
                }
            }
        }

    return groups
}