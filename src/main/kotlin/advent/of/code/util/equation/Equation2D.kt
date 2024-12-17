package advent.of.code.util.equation

data class Equation(val x: Long, val y: Long, val c: Long)
data class Equation2D(val equation1: Equation, val equation2: Equation) {

    fun solve(): Pair<Double, Double> {
        val x = (equation1.c * equation2.y - equation2.c * equation1.y).toDouble() /
                (equation1.x * equation2.y - equation2.x * equation1.y)
        val y = (equation1.x * equation2.c - equation2.x * equation1.c).toDouble() /
                (equation1.x * equation2.y - equation2.x * equation1.y)
        return Pair(x, y)
    }
}


fun Pair<Double, Double>.canBeSolved(): Boolean {
    val (n1, n2) = this
    return n1.toLong().toDouble() == n1 && n2.toLong().toDouble() == n2
}