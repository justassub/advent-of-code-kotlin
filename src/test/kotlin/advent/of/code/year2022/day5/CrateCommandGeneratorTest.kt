package advent.of.code.year2022.day5

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CrateCommandGeneratorTest {

    @Test
    fun createCrateCommand() {
        val text = "move 3 from 1 to 3"
        val result = CrateCommandGenerator.createCommand(text)
        val expectedResult = CrateCommand(1, 3, 3)

        Assertions.assertEquals(expectedResult, result)
    }
}
