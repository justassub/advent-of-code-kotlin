package advent.of.code.year2022.day9


data class Position(val x: Int, val y: Int)


operator fun Position.plus(other: Position) = Position(this.x + other.x, this.y + other.y)
operator fun Position.minus(other: Position) = Position(this.x - other.x, this.y - other.y)
