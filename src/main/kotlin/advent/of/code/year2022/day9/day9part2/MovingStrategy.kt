package advent.of.code.year2022.day9.day9part2

import advent.of.code.year2022.day9.*
import kotlin.math.abs

interface MovingStrategy {

    fun move(currentPosition: Position, direction: Direction): Position
}

class HeadStrategy : MovingStrategy {
    override fun move(currentPosition: Position, direction: Direction): Position {
        return currentPosition.headMove(direction)
    }

}

abstract class BaseMoveStrategy : MovingStrategy {
    protected fun shouldMove(currentPosition: Position, masterPosition: Position): Boolean {

        val xDifference = abs(masterPosition.x - currentPosition.x)
        val yDifference = abs(masterPosition.y - currentPosition.y)

        return (xDifference > 1 || yDifference > 1)
    }
}

class HeadFollowerStrategy(private val master: Movable) : BaseMoveStrategy() {
    override fun move(currentPosition: Position, direction: Direction): Position {
        return if (super.shouldMove(currentPosition, master.getCurrentPosition())) {
            master.getPreviousPosition()
        } else {
            currentPosition
        }
    }

}

class KnotFollowerStrategy(private val master: Movable) : BaseMoveStrategy() {
    override fun move(currentPosition: Position, direction: Direction): Position {
        return if (super.shouldMove(currentPosition, master.getCurrentPosition())) {
            return calculateNextPosition(currentPosition, direction)
        } else {
            currentPosition
        }
    }

    private fun calculateNextPosition(currentPosition: Position, direction: Direction): Position {
        val masterPosition = master.getCurrentPosition()
        return if (currentPosition.x == masterPosition.x || currentPosition.y == masterPosition.y) {
            currentPosition.headMove(direction)
        } else {
            val difference = masterPosition - currentPosition

            if (difference.x > 0 && difference.y > 0) {
                currentPosition + Position(1, 1)
            } else if (difference.x > 0 && difference.y < 0) {
                currentPosition + Position(1, -1)
            } else if (difference.x < 0 && difference.y > 0) {
                currentPosition + Position(-1, 1)
            } else {
                currentPosition + Position(-1, -1)
            }
        }
    }
}


fun Position.headMove(direction: Direction): Position {
    return this + when (direction) {
        Direction.UP -> Position(0, 1)
        Direction.DOWN -> Position(0, -1)
        Direction.RIGHT -> Position(1, 0)
        Direction.LEFT -> Position(-1, 0)
    }
}

fun Position.moveStraight(direction: Direction): Position {
    return this + when (direction) {
        Direction.UP -> Position(1, 1)
        Direction.DOWN -> Position(1, -1)
        Direction.RIGHT -> Position(1, 0)
        Direction.LEFT -> Position(-1, 0)
    }
}
