package advent.of.code.util

fun List<Long>.multiply(): Long = reduce { acc, l -> acc * l }
fun List<Int>.multiply(): Int = reduce { acc, l -> acc * l }

fun <T> List<T>.createPossibleCombinationsWithoutSelf(): List<Pair<T, T>> {
    return this.flatMapIndexed { index, t -> this.drop(index + 1).map { t to it } }
}