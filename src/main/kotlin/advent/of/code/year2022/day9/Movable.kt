package advent.of.code.year2022.day9

interface Movable {
    fun moveStep(moveDirection: Direction)
    fun getCurrentPosition(): Position
    fun getPreviousPosition(): Position
}
