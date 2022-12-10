package advent.of.code.year2022.day10


enum class SignalType(val requiredCycles: Int) {
    NOOP(1), ADDX(2)
}

data class SignalAction(val signalType: SignalType, val value: Int)


object SignalGenerator {
    fun generateSignal(line: String): SignalAction {
        return if (line.contains("noop")) {
            SignalAction(SignalType.NOOP, 0)
        } else {
            SignalAction(SignalType.ADDX, line.substringAfter(" ").toInt())
        }
    }
}


