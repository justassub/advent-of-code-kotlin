package advent.of.code.util

data class MinMaxPointValues(val minX: Int, val maxX: Int, val minY: Int, val maxY: Int)


fun Map<Point, Any>.findMinMaxValues() = MinMaxPointValues(
    this.minOf { it.key.x },
    this.maxOf { it.key.x },
    this.minOf { it.key.y },
    this.maxOf { it.key.y }
)