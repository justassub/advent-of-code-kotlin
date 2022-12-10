package advent.of.code.year2022.day10

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SignalGeneratorTest {

    @Test
    fun `Should create proper ADDX Signal action `() {
        val line = "addx -11"
        val result = SignalGenerator.generateSignal(line)

        Assertions.assertEquals(SignalType.ADDX, result.signalType)
        Assertions.assertEquals(-11, result.value)

    }

    @Test
    fun `Should create proper NOOP Signal action `() {
        val line = "noop"
        val result = SignalGenerator.generateSignal(line)

        Assertions.assertEquals(SignalType.NOOP, result.signalType)
        Assertions.assertEquals(0, result.value)

    }
}