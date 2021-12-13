package advent.of.code.year2021

data class Point(val x: Int, val y: Int)

data class PointOrMark(val x: Int, val y: Int, val isMark: Boolean) {
    fun extractSymbol(): String = if (isMark) "#" else " "
}
