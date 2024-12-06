package advent.of.code.util

fun List<Long>.multiply(): Long = reduce { acc, l -> acc * l }
fun List<Int>.multiply(): Int = reduce { acc, l -> acc * l }

fun <T> List<T>.createPossibleCombinationsWithoutSelf(): List<Pair<T, T>> {
    return this.flatMapIndexed { index, t -> this.drop(index + 1).map { t to it } }
}

fun Collection<Point>.findMaxX() = maxOf { it.x }
fun Collection<Point>.findMaxY() = maxOf { it.y }
fun Collection<Point>.findMinX() = minOf { it.x }
fun Collection<Point>.findMinY() = minOf { it.y }

fun Map<Point,Any?>.findMaxX()=keys.maxOf { it.x }
fun Map<Point,Any?>.findMaxY()=keys.maxOf { it.y }
fun Map<Point,Any?>.findMinX()=keys.minOf { it.x }
fun Map<Point,Any?>.findMinY()=keys.minOf { it.y }