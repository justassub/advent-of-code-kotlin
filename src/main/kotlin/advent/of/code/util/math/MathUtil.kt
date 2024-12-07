package advent.of.code.util.math


object MathUtil {

    fun solveEvaluationWithoutParenthesesNoPriority(expression: String, separator: String = " "): Long {
        val parts = expression.split(separator)
        return parts.drop(1)
            .chunked(2)
            .fold(parts[0].toLong()) { acc, (operator, number) ->
                when (operator) {
                    "+" -> acc + number.toLong()
                    "-" -> acc - number.toLong()
                    "*" -> acc * number.toLong()
                    "/" -> acc / number.toLong()
                    else -> throw IllegalArgumentException("Unknown operator: $operator")
                }
            }
    }
}