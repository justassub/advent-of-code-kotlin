package advent.of.code.year2022.day8

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TreeGeneratorTest {

    @Test
    fun `Should create proper trees`() {
        val text = """
            30373
            25512
            65332
            33549
            35390
        """.trimIndent()
            .lines()
        val result = ForestGenerator.generateForest(text)

        Assertions.assertEquals(25, result.size)
        Assertions.assertEquals(Tree(0, 0, 3), result.first())
        Assertions.assertEquals(Tree(2, 2, 3), result[12])
    }
}
