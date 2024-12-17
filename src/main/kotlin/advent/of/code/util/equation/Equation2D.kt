package advent.of.code.util.equation

data class Equation(val x: Int, val y: Int, val c: Int)
data class Equation2D(val equation1: Equation, val equation2: Equation) {

    fun solve(): Pair<Double, Double> {
        val x = (equation1.c * equation2.y - equation2.c * equation1.y).toDouble() /
                (equation1.x * equation2.y - equation2.x * equation1.y)
        val y = (equation1.x * equation2.c - equation2.x * equation1.c).toDouble() /
                (equation1.x * equation2.y - equation2.x * equation1.y)
        return Pair(x, y)
    }
}