package advent.of.code.year2022.day5

import org.junit.jupiter.api.Test

class OriginalCrateStackGeneratorTest {
    @Test
    fun `Should create initiatedCrateStacks`() {
        val text = """
    [D]    
[N] [C]    
[Z] [M] [P]
        """.trimIndent()

        val stacks = OriginalCrateStackGenerator.generateOriginalStacks(text.lines())
    }
}
