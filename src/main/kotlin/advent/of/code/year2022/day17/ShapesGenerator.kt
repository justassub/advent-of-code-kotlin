package advent.of.code.year2022.day17

import advent.of.code.util.Point

object ShapesGenerator {

    fun getDefaultShapes(): Set<(Point) -> Shape> {
        return setOf(
            { MinusShape(it) },
            { PlusShape(it) },
            { LShape(it) },
            { IShape(it) },
            { SquareShape(it) }
        )
    }
}