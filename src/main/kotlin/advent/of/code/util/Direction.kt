package advent.of.code.util


fun Char.isDirection(): Boolean =
    try {
        createDirection()
        true
    } catch (e: IllegalArgumentException) {
        false
    }


fun Char.createDirection(): Direction {
    return when (this) {
        'R', '>' -> Direction.RIGHT
        'U', '^' -> Direction.UP
        'L', '<' -> Direction.LEFT
        'D', 'v' -> Direction.DOWN

        else -> throw IllegalArgumentException("Bad character $this")
    }
}

enum class Direction {
    UP, RIGHT, DOWN, LEFT
}

enum class LeftOrRight {
    LEFT, RIGHT
}


object Builder {
    fun leftOrRightBuilderSingleLine(
        data: String,
        leftSymbol: Char,
        rightSymbol: Char
    ): List<LeftOrRight> {
        return data.map {
            when (it) {
                leftSymbol -> LeftOrRight.LEFT
                rightSymbol -> LeftOrRight.RIGHT
                else -> throw IllegalArgumentException("Bad symbol $it")
            }
        }
    }
}