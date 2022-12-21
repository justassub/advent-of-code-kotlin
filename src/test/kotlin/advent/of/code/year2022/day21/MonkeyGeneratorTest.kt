package advent.of.code.year2022.day21

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MonkeyGeneratorTest {
    @Test
    fun shouldCreateSimpleMonkey() {
        val data = "gzdg: 1".lines()

        val result = MonkeyGenerator.createMonkeys(data)

        Assertions.assertEquals(1, result.size)
        Assertions.assertTrue(result.containsKey("gzdg"))

        val monkey = result.getValue("gzdg")
        Assertions.assertTrue(monkey is SimpleMonkey)
        Assertions.assertEquals(1, monkey.getDigit(result))
    }

    @Test
    fun shouldCreateComplexMonkeys() {
        val data = """
            root: pppw + sjmn
            pppw: 5
            sjmn: 9
        """.trimIndent()
            .lines()

        val result = MonkeyGenerator.createMonkeys(data)

        Assertions.assertEquals(3, result.size)
        Assertions.assertTrue(result.containsKey("root"))

        val monkey = result.getValue("root")
        Assertions.assertTrue(monkey is ComplexMonkey)
        Assertions.assertEquals(14, monkey.getDigit(result))
    }
}
