package advent.of.code.year2023.day9

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SequenceGeneratorTest {
    @Test
    fun `Should match consistent number generator`() {
        val numbers = listOf(0, 3, 6, 9, 12, 15)
        val sequenceGenerator = ConstantNumberGenerator(numbers)

        assertTrue(sequenceGenerator.isMatching())
        assertEquals(18, sequenceGenerator.generateNext())
    }


    @Test
    fun `Should  NOT match consistent number generator`() {
        val numbers = listOf(1, 3, 6, 10, 15, 21)
        val sequenceGenerator = ConstantNumberGenerator(numbers)

        assertFalse(sequenceGenerator.isMatching())
    }


    @Test
    fun `Should calculate custom numbers 1`() {
        val numbers = listOf(1, 3, 6, 10, 15, 21)
        val sequenceGenerator = CustomNumbersGenerator(numbers)

        assertTrue(sequenceGenerator.isMatching())
        assertEquals(28, sequenceGenerator.generateNext())
    }

    @Test
    fun `Should calculate custom numbers 2`() {
        val numbers = listOf(10, 13, 16, 21, 30, 45)
        val sequenceGenerator = CustomNumbersGenerator(numbers)

        assertTrue(sequenceGenerator.isMatching())
        assertEquals(68, sequenceGenerator.generateNext())
    }

    @Test
    fun `Should calculate custom numbers 3`() {
        val numbers = listOf(0, 3, 6, 9, 12, 15)
        val sequenceGenerator = CustomNumbersGenerator(numbers)
        assertEquals(18, sequenceGenerator.generateNext())
    }

    @Test
    fun `Should calculate FIRST custom numbers 1`() {
        val numbers = listOf(0, 2, 4, 6)
        val sequenceGenerator = ConstantNumberGenerator(numbers)

        assertEquals(-2, sequenceGenerator.generateFirst())
    }

    @Test
    fun `Should calculate FIRST custom numbers 2`() {
        val numbers = listOf(0, 2, 4, 6)
        val sequenceGenerator = CustomNumbersGenerator(numbers)

        assertEquals(-2, sequenceGenerator.generateFirst())
    }

    @Test
    fun `Should calculate FIRST custom numbers 3`() {
        val numbers = listOf(10, 13, 16, 21, 30, 45)
        val sequenceGenerator = CustomNumbersGenerator(numbers)

        assertEquals(5, sequenceGenerator.generateFirst())
    }
}
