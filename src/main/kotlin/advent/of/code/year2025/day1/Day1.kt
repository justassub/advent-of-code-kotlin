package advent.of.code.year2025.day1

import advent.of.code.ContentReader

fun main() {
    val data = ContentReader.readFileAsLines(2025, 1).map { it[0] to it.drop(1) }
        .map { SafeDial(SafeDialDirection.fromChar(it.first), it.second.toInt()) }


    val startingPoint = 50
    val targetPoint = 0
    var howManyTargetsReached = 0

    val activateAction = { point: Int ->
        if (point == targetPoint) howManyTargetsReached++
    }
    turnBySimpleDials(startingPoint, data, 100, activateAction)
    print(howManyTargetsReached)
}

private fun turnBySimpleDials(
    startPoint: Int,
    dials: List<SafeDial>,
    maxValue: Int, activateAction: (Int) -> Unit
): Int {
    return dials.fold(startPoint) { acc, dial ->
        when (dial.direction) {
            SafeDialDirection.LEFT -> (acc - dial.steps)
            SafeDialDirection.RIGHT -> (acc + dial.steps)
        }.mod(maxValue)
            .apply {
                activateAction(this)
            }
    }
}

private data class SafeDial(
    val direction: SafeDialDirection, val steps: Int
)

private enum class SafeDialDirection {
    LEFT, RIGHT;

    companion object {
        fun fromChar(char: Char): SafeDialDirection {
            return when (char) {
                'L' -> LEFT
                'R' -> RIGHT
                else -> throw IllegalArgumentException("Invalid direction")
            }
        }
    }
}