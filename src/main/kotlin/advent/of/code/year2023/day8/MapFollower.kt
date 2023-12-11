package advent.of.code.year2023.day8

import advent.of.code.util.LeftOrRight

class MapFollower(
    private val map: Map<String, Pair<String, String>>,
    private val stepsToFollow: List<LeftOrRight>
) {


    fun calculateMinSteps(goal: String): Long {
        var positionStart = "AAA" to 0L

        while (true) {
            val result = calculateFirstOfGoalOrNull(positionStart)

            if (result.any { it.first == goal }) {
                return result.first { it.first == goal }.second
            }

            positionStart = result.last()
        }
    }

    private fun calculateFirstOfGoalOrNull(startPosition: Pair<String, Long>): List<Pair<String, Long>> {
        return stepsToFollow.runningFold(
            initial = startPosition
        ) { acc, leftOrRight ->
            val goal = acc.first
            val nextGoalWays = map[goal]!!

            if (leftOrRight == LeftOrRight.LEFT) {
                Pair(nextGoalWays.first, acc.second + 1)
            } else {
                Pair(nextGoalWays.second, acc.second + 1)
            }
        }

    }
}