package advent.of.code.year2022.day21

object MonkeyGenerator {

    fun createMonkeys(data: List<String>): Map<String, AnyMonkey> {
        return data.associate { createMonkey(it) }
    }

    private fun createMonkey(line: String): Pair<String, AnyMonkey> {
        val data = line.split(": ")
        val name = data.first()
        return data.last().toLongOrNull()
            ?.let { name to SimpleMonkey(it) }
            ?: name to createComplexMonkey(data.last())
    }

    private fun createComplexMonkey(data: String): ComplexMonkey {
        val otherMonkeysAndMathAction = data.split(" ")
        return ComplexMonkey(
            otherMonkeysAndMathAction[1].toArithmeticAction(),
            otherMonkeysAndMathAction.first() to otherMonkeysAndMathAction.last()
        )
    }
}


private fun String.toArithmeticAction(): ArithmeticAction {
    return when (this) {
        "-" -> ArithmeticAction.SUB
        "+" -> ArithmeticAction.SUM
        "/" -> ArithmeticAction.DIV
        "*" -> ArithmeticAction.MUL
        else -> throw IllegalArgumentException("Wrong input")
    }
}
