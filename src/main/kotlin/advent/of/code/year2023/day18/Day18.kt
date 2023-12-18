package advent.of.code.year2023.day18

import advent.of.code.ContentReader
import advent.of.code.util.Direction
import advent.of.code.util.Point
import advent.of.code.util.createDirection
import advent.of.code.util.printResult
import kotlin.math.max
import kotlin.math.min

fun main() {
    val lines = ContentReader.readFileAsLines(2023, 18)
        .map { it.split(" ") }
        .map { createSinglePlanInput(it) }
        .runningFold(Marker(Point(0, 0), Point(0, 0), "START")) { start: Marker, digitInput: DigitInput ->
            createNextMarker(start, digitInput)
        }


    println()
}





fun createNextMarker(start: Marker, digitInput: DigitInput): Marker {
    val startPoint = start.finish
    val finish = when (digitInput.direction) {
        Direction.RIGHT -> startPoint.copy(x = startPoint.x + digitInput.amount)
        Direction.UP -> startPoint.copy(y = startPoint.y - digitInput.amount)
        Direction.DOWN -> startPoint.copy(y = startPoint.y + digitInput.amount)
        Direction.LEFT -> startPoint.copy(x = startPoint.x - digitInput.amount)
    }
    return Marker(
        start = startPoint,
        finish = finish,
        color = digitInput.colour
    )
}

fun createSinglePlanInput(data: List<String>): DigitInput {
    return DigitInput(
        data.first().first().createDirection(),
        data[1].toInt(),
        data.last()
    )
}

data class DigitInput(
    val direction: Direction,
    val amount: Int,
    val colour: String
)


data class Marker(
    val start: Point,
    val finish: Point,
    val color: String
)