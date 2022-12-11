package advent.of.code.year2022.day9

import advent.of.code.year2022.ContentReader2022
import advent.of.code.year2022.day9.day9part2.HeadFollowerStrategy
import advent.of.code.year2022.day9.day9part2.HeadStrategy
import advent.of.code.year2022.day9.day9part2.KnotFollowerStrategy

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
    val resultPart2 = solvePart2(actions)
    println(resultPart2)
}

fun createRope(amount: Int): List<Movable> {
    val master = MovingPart(HeadStrategy())
    var follower = MovingPart(HeadFollowerStrategy(master))
    val parts = mutableListOf(master, follower)

    repeat(amount) {
        val m = MovingPart(KnotFollowerStrategy(follower))
        parts.add(m)
        follower = m
    }

    return parts
}

fun solvePart1(actions: List<MoveAction>): Int {
    val rope = createRope(0)

    return actions.flatMap {
        moveAllAndGetLastFolowerPosition(rope, it)
    }
        .distinct()
        .count()

}

fun solvePart2(actions: List<MoveAction>): Int {
    val rope = createRope(8)
    return actions.flatMap {
        moveAllAndGetLastFolowerPosition(rope, it)
    }
        .distinct()

        .let {
            println(it)
            it
        }
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


