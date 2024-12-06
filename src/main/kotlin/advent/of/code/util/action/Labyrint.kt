package advent.of.code.util.action

import advent.of.code.util.*
import advent.of.code.year2022.day8.takeWhileInclusive


class Labyrint(
    var player: Point,
    val obs: Set<Point>,
    startDirection: Direction = Direction.UP,
    val newDirectionSupplier: (Direction) -> Direction = { it.nextDirectionClockWise() }
) {

    private val maxX = obs.findMaxX()
    private val maxY = obs.findMaxY()
    private val minX = obs.findMinX()
    private val minY = obs.findMinY()

    var direction = startDirection

    private val history = mutableSetOf(player to direction)

    fun findVisitedLocationsToExit(): Set<Point> {
        return generateSequence { moveToNextLocationWithHistory() }
            .takeWhileInclusive { it.first.second.not() }
            .flatMap {
                direction = newDirectionSupplier(direction)
                player = it.first.first
                it.second
            }.toSet()
    }

    fun isInfiniteLoop(): Boolean {
        while (true) {
            player = moveToNextLocation() ?: return false
            direction = direction.nextDirectionClockWise()

            if (history.add(player to direction).not()) {
                return true
            }
        }
    }

    private fun moveToNextLocation(): Point? {
        return slideToDirectionByMapToObstacleNoHistory(player, direction, obs, maxX, maxY, minX, minY)
            .takeUnless { it.second }
            ?.first
    }

    private fun moveToNextLocationWithHistory(): Pair<Pair<Point, Boolean>, Set<Point>> {
        return slideToDirectionByMapToObstacleHistory(player, direction, obs, maxX, maxY, minX, minY)
    }
}
