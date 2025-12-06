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
                    "concatenation" -> "$acc$number".toLong()
                    else -> throw IllegalArgumentException("Unknown operator: $operator")
                }
            }
    }
}

enum class Operator {
    ADDITION, MULTIPLICATION, DIVISION, SUBTRACTION;

    fun applyNumbers(d1: Long, d2: Long): Number {
        return when (this) {
            ADDITION -> d1 + d2
            MULTIPLICATION -> d1 * d2
            DIVISION -> d1 / d2
            SUBTRACTION -> d1 - d2
        }
    }

    companion object {
        fun fromString(string: String): Operator {
            return when (string) {
                "+" -> ADDITION
                "*" -> MULTIPLICATION
                "/" -> DIVISION
                "-" -> SUBTRACTION
                else -> throw IllegalArgumentException("Unknown operator: $string")
            }
        }
    }

}

fun Collection<Number>.applyOperator(operator: Operator): Long {
    return this.reduce { acc, n -> operator.applyNumbers(acc.toLong(), n.toLong()) } as Long
}

data class MathOperation(
    val numbers: List<Long>,
    val operator: Operator,
) {
    fun applyOperation(): Long {
        return numbers.reduce { acc, n -> operator.applyNumbers(acc, n).toLong() }
    }
}