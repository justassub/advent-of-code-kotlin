package advent.of.code.year2023.day14

import advent.of.code.util.Direction
import advent.of.code.util.Point
import advent.of.code.util.recreateByDirectionReverse

interface Movable {
    fun move(direction: Direction, allRocks: List<Rock>)
}

abstract class Rock {
    abstract fun getCurrentPosition(): Point
}

class RoundRock(
    private val originalPosition: Point
) : Movable, Rock() {
    private var currentPosition = originalPosition
    var isStuck = false

    override fun move(direction: Direction, allRocks: List<Rock>) {
        if (!canMoveUp(allRocks)) {
            isStuck = true
            return
        }
        currentPosition = currentPosition.recreateByDirectionReverse(direction)
    }

    private fun canMoveUp(allRocks: List<Rock>): Boolean {
        return when {
            currentPosition.y == 0 -> false
            allRocks.any {
                it.getCurrentPosition().y == this.getCurrentPosition().y - 1 && it.getCurrentPosition().x == this.getCurrentPosition().x
            } -> false

            else -> true
        }
    }

    override fun getCurrentPosition() = currentPosition


}

class SquareRock(private val originalPosition: Point) : Rock() {
    override fun getCurrentPosition() = originalPosition
}