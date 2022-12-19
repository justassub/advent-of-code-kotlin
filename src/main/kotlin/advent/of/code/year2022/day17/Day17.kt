package advent.of.code.year2022.day17

import advent.of.code.util.Direction
import advent.of.code.year2022.ContentReader2022
import java.util.*
import kotlin.system.measureTimeMillis

const val day = 17


fun main() {

    val timeInMillis = measureTimeMillis { solve() }

    println("Task 17 finished in $timeInMillis ms")
}

fun solve() {
    val actions = ContentReader2022.readFileAsText(day)
        .map { buildActions(it) }

    println(solvePart1(actions))

}

fun solvePart1(actions: List<Direction>): Int {
    val infinityShapesGeneration = generateSequence { ShapesGenerator.getDefaultShapes() }.flatMap { it }
    val tetris = Tetris(infinityShapesGeneration, LinkedList(actions))
    tetris.play(11)
    return tetris.getPeak()
}

fun buildActions(c: Char): Direction {
    return when (c) {
        '>' -> Direction.RIGHT
        '<' -> Direction.LEFT
        else -> throw IllegalArgumentException("Something wrong with char: $c")
    }
}