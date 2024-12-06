package advent.of.code.util

val clockWiseDirections = listOf(Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT)

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

enum class ALLDirections {
    UP, RIGHT, DOWN, LEFT, UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT
}

enum class Direction {
    UP, RIGHT, DOWN, LEFT;

    fun nextDirectionClockWise(): Direction {
        return when (this) {
            UP -> RIGHT
            RIGHT -> DOWN
            DOWN -> LEFT
            LEFT -> UP
        }
    }
    fun reverseDirection(): Direction {
        return when (this) {
            UP -> DOWN
            RIGHT -> LEFT
            DOWN -> UP
            LEFT -> RIGHT
        }
    }
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