package advent.of.code.year2022.day3

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PriorityCalculatorTest {
    @Test
    fun `Should return 0 on empty chars`() {
        val result = PriorityCalculator.funCalculatePriority(listOf())
        Assertions.assertEquals(0, result)
    }
    @Test
    fun `Should return 27 on 'a' and 'z'`(){
        val result = PriorityCalculator.funCalculatePriority(listOf('a','z'))
        Assertions.assertEquals(27, result)
    }
    @Test
    fun `Should return 79 on 'A' and 'Z'`(){
        val result = PriorityCalculator.funCalculatePriority(listOf('A','Z'))
        Assertions.assertEquals(79, result)
    }
}
