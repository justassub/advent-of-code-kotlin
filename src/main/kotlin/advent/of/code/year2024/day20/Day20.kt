package advent.of.code.year2024.day20

import advent.of.code.util.*

fun main() {
    val points = PointBuilder.createPointsFromFile(2024, 20)
        .let { PointBuilder.traversablePointsFromMapSimpleDirections(it) { c -> c } }
    val start = points.first { it.value == 'S' }
    val goal = points.first { it.value == 'E' }


    val minimumSteps = findShortestPath(start, goal, { lit -> lit.filter { it.value != '#' } })
    val mustSaveSeconds = 0


    findAllPaths(
        MovingData(start, 0, 1),
        goal,
        { if (it.allowsToEnter > 0) it.point.findAllNeighbours() else it.point.findNeighbours { n -> n.value != '#' } },
        { point, neighbour ->
            MovingData(
                neighbour,
                point.seconds + 1,
                if (neighbour.value == '#') point.allowsToEnter - 1 else point.allowsToEnter
            )
        },
        { it.seconds < minimumSteps - mustSaveSeconds }
    )
        .printResult()

}

//fun <T> findAllPathsWithTeleportationShorterThan(
//    start: MovingData,
//    goal: TraversablePoint<T>,
//    mustBeShorterThan: Int,
//    onGoalCallback: (MovingData) -> Unit,
//) {
//    val queue = LinkedList(listOf(start))
//    val seen = mutableSetOf<Point>()
//    while (queue.isNotEmpty()) {
//        val current = queue.poll()
//        if (current.seconds >= mustBeShorterThan) {
//            continue
//        }
//        if (current.point == goal) {
//            onGoalCallback(current)
//            continue
//        }
//
//        val simpleWalks = current.point.findAllNeighbours().filter { it.value != '#' }
//            .map { MovingData(it, current.seconds + 1, current.teleports) }
//
//        val teleportWalks =
//            (if (current.teleports > 0) current.point.findNeighbours { it.value == '#' } else emptyList())
//                .map { MovingData(it, current.seconds + 1, current.teleports - 1) }
//
//        (simpleWalks + teleportWalks)
//            .filter { seen.add(it.point.point) }
//            .forEach {
//                queue.add(it)
//            }
//    }
//}

