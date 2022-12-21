package advent.of.code.year2022.day21


abstract class AnyMonkey {
    abstract fun getDigit(monkeys: Map<String, AnyMonkey>): Long
}

class SimpleMonkey(var number: Long) : AnyMonkey() {
    override fun getDigit(monkeys: Map<String, AnyMonkey>): Long {
        return number
    }
}

class ComplexMonkey(private val arithmeticAction: ArithmeticAction, private val otherMonkeys: Pair<String, String>) :
    AnyMonkey() {

    fun bothNumbersAreSame(monkeys: Map<String, AnyMonkey>): Boolean {
        return monkeys.getValue(otherMonkeys.first).getDigit(monkeys) == monkeys.getValue(otherMonkeys.second)
            .getDigit(monkeys)
    }

    override fun getDigit(monkeys: Map<String, AnyMonkey>): Long {
        return makeMathAction(
            monkeys.getValue(otherMonkeys.first).getDigit(monkeys),
            monkeys.getValue(otherMonkeys.second).getDigit(monkeys)
        )
    }

    private fun makeMathAction(n1: Long, n2: Long): Long {
        return when (arithmeticAction) {
            ArithmeticAction.SUM -> n1 + n2
            ArithmeticAction.SUB -> n1 - n2
            ArithmeticAction.MUL -> n1 * n2
            ArithmeticAction.DIV -> n1 / n2
        }
    }

}


enum class ArithmeticAction {
    SUM, SUB, MUL, DIV
}

