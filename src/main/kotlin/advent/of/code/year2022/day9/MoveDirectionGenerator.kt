package advent.of.code.year2022.day9

object MoveDirectionGenerator {
    fun generateMoveActionsForMaster(lines: List<String>): List<MoveAction> {
        return lines.map { createMoveAction(it) }
    }

    private fun createMoveAction(line: String): MoveAction {
        return MoveAction(line.drop(2).toInt(), createDirection(line.first()))
    }

    private fun createDirection(c: Char): Direction {
        return when (c) {
            'R' -> Direction.RIGHT
            'U' -> Direction.UP
            'L' -> Direction.LEFT
            'D' -> Direction.DOWN
            else -> throw IllegalArgumentException("Bad character $c")
        }
    }
}
