package advent.of.code.year2022.day20

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class NumberMixerTest {
    @Test
    fun `Should be example`() {
        val list = listOf(1, 2, -3, 3, -2, 0, 4)
        val result = NumberMixer.mixNumbers(list)

        Assertions.assertEquals(1, result)
    }
}
