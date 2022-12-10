package advent.of.code.year2022.day9

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MoveActionGeneratorTest {
    @Test
    fun `Should create directions`() {
        val lines = """
            D 14
            U 13
            R 166
            L 8
        """.trimIndent().lines()

        val result = MoveDirectionGenerator.generateMoveActionsForMaster(lines)
        Assertions.assertEquals(4, result.size)
        Assertions.assertEquals(MoveAction(14, Direction.DOWN), result[0])
        Assertions.assertEquals(MoveAction(13, Direction.UP), result[1])
        Assertions.assertEquals(MoveAction(166, Direction.RIGHT), result[2])
        Assertions.assertEquals(MoveAction(8, Direction.LEFT), result[3])
    }
}
