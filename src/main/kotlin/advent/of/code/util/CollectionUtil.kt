package advent.of.code.util

fun List<Long>.multiply(): Long = reduce { acc, l -> acc * l }
fun List<Int>.multiply(): Int = reduce { acc, l -> acc * l }