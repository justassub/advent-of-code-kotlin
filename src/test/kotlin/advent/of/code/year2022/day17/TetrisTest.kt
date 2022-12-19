package advent.of.code.year2022.day17

import advent.of.code.util.Direction
import advent.of.code.util.Point
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

class TetrisTest {

    @Test
    fun playTetrisWithShapeOne() {
        val forms: Set<(Point) -> Shape> =
            setOf { MinusShape(it) }

        val tetris = Tetris(forms.asSequence(), createWind())

        tetris.play(1)

        Assertions.assertEquals(1, tetris.getPeak())
    }

    @Test
    fun playTetrisWith2Shapes() {
        val forms: Set<(Point) -> Shape> =
            setOf(
                { MinusShape(it) },
                { PlusShape(it) }
            )

        val tetris = Tetris(forms.asSequence(), createWind())

        tetris.play(2)

        Assertions.assertEquals(4, tetris.getPeak())
    }

    @Test
    fun playTetrisWith3Shapes() {
        val forms: Set<(Point) -> Shape> =
            setOf(
                { MinusShape(it) },
                { PlusShape(it) },
                { LShape(it) }

            )

        val tetris = Tetris(forms.asSequence(), createWind())

        tetris.play(3)

        Assertions.assertEquals(6, tetris.getPeak())
    }


    @Test
    fun playTetrisWith4Shapes() {
        val forms: Set<(Point) -> Shape> =
            setOf(
                { MinusShape(it) },
                { PlusShape(it) },
                { LShape(it) },
                { IShape(it) },

                )

        val tetris = Tetris(forms.asSequence(), createWind())

        tetris.play(4)

        Assertions.assertEquals(7, tetris.getPeak())
    }

    @Test
    fun playTetrisWith5Shapes() {


        val tetris = Tetris(generateSequence { ShapesGenerator.getDefaultShapes() }.flatMap { it }, createWind())

        tetris.play(5)

        Assertions.assertEquals(9, tetris.getPeak())
    }

    @Test
    fun playTetrisWith2022Shapes() {


        val tetris = Tetris(generateSequence { ShapesGenerator.getDefaultShapes() }.flatMap { it }, createWind())

        tetris.play(2022)

        Assertions.assertEquals(3068, tetris.getPeak())
    }

    private fun createWind(): Queue<Direction> {
        return LinkedList(">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>".map { buildActions(it) })
    }
}