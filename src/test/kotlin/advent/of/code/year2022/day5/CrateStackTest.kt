package advent.of.code.year2022.day5

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CrateStackTest {
    @Test
    fun `Should add two crates`() {
        val createStack = CrateStack()

        createStack.addNumberOfCratesOnTop(listOf('A', 'B'))
        Assertions.assertEquals(2, createStack.getCrates().size)
        Assertions.assertSame(listOf('A', 'B'), createStack.getCrates())
    }

    @Test
    fun `Should remove last two creates`() {
        val createStack = CrateStack()

        createStack.addNumberOfCratesOnTop(listOf('A', 'B', 'C'))
        val result = createStack.retrieveLastNAndReverse(2)
        Assertions.assertEquals(2, result.size)
        Assertions.assertEquals(listOf('C', 'B'), result)
        Assertions.assertEquals(listOf('A'), createStack.getCrates())
    }
}
