package advent.of.code.year2022.day9

import advent.of.code.year2022.day9.day9part2.MovingStrategy

class MovingPart(private val movingStrategy: MovingStrategy) : Movable {

    private var currentPosition = Position(0, 0)
    private var previousPosition = Position(0, 0)
    override fun moveStep(moveDirection: Direction) {
        previousPosition = currentPosition
        currentPosition = movingStrategy.move(currentPosition, moveDirection)
    }


    override fun getCurrentPosition(): Position {
        return currentPosition
    }

    override fun getPreviousPosition(): Position {
        return this.previousPosition
    }


}
