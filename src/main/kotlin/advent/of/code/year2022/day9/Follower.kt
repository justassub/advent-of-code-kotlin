package advent.of.code.year2022.day9

import kotlin.math.abs

class Follower(private val master: Movable?) : Movable {
    private var x = 0;
    private var y = 0
    private var previousPosition = Position(x, y)
    override fun moveStep(moveDirection: Direction) {
        previousPosition = Position(x, y)
        if (master == null) {
            move(moveDirection)
            return
        }
        if (!shouldMove()) {
            return
        }


        x = master.getPreviousPosition().x
        y = master.getPreviousPosition().y
    }


    private fun shouldMove(): Boolean {
        val masterPosition = master!!.getCurrentPosition()
        val xDifference = abs(masterPosition.x - x)
        val yDifference = abs(masterPosition.y - y)

        return (xDifference > 1 || yDifference > 1)
    }

    override fun getCurrentPosition(): Position {
        return Position(x, y)
    }

    override fun getPreviousPosition(): Position {
        return this.previousPosition
    }


    private fun move(direction: Direction) {
        when (direction) {
            Direction.UP -> y++
            Direction.DOWN -> y--
            Direction.RIGHT -> x++
            Direction.LEFT -> x--
        }
    }
}
