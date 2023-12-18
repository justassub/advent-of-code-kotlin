package advent.of.code.util



fun Char.createDirection(): Direction {
    return when (this) {
        'R' -> Direction.RIGHT
        'U' -> Direction.UP
        'L' -> Direction.LEFT
        'D' -> Direction.DOWN
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