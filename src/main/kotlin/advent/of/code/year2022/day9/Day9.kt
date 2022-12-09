package advent.of.code.year2022.day9

import advent.of.code.year2022.ContentReader2022

import kotlin.system.measureTimeMillis

const val day = 9

fun main() {
    val timeInMillis = measureTimeMillis { solve() }

    println("Task 9 finished in $timeInMillis ms")
}

fun solve() {

    val actions = ContentReader2022.readFileAsLines(day)
        .let { MoveDirectionGenerator.generateMoveActionsForMaster(it) }

    val resultPart1 = solvePart1(actions)
    println(resultPart1)

}


fun solvePart1(actions: List<MoveAction>): Int {
    val master = Follower(null)
    val follower = Follower(master)
    val rope = listOf(master, follower)

    return actions.flatMap {
        moveAllAndGetLastFolowerPosition(rope, it)
    }
        .distinct()
        .count()

}

fun moveAllAndGetLastFolowerPosition(list: List<Movable>, action: MoveAction): List<Position> {
    return (0 until action.amount).map {
        moveAllAndGetTail(list, action.direction)
    }
}

fun moveAllAndGetTail(movers: List<Movable>, direction: Direction): Position {
    movers.forEach { it.moveStep(direction) }
    return movers.last().getCurrentPosition()
}


