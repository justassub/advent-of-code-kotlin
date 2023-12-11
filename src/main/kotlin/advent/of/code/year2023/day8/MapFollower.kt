package advent.of.code.year2023.day8

import advent.of.code.util.LeftOrRight

class MapFollower(
    private val map: Map<String, Pair<String, String>>,
    private val stepsToFollow: List<LeftOrRight>
) {


    fun calculateMinSteps(
        startPosition: String,
        cardMapFunction: (position: String) -> Boolean
    ): Long {
        var starting = startPosition to 0L
        for (leftOrRight in generateSequence { stepsToFollow }.flatten()) {
            starting = getNextGoal(starting, leftOrRight)

            if (cardMapFunction(starting.first)) {
                return starting.second
            }
        }
        throw IllegalArgumentException("Should not happen")
    }


    private fun getNextGoal(
        acc: Pair<String, Long>,
        leftOrRight: LeftOrRight
    ): Pair<String, Long> {
        val goal = acc.first
        val nextGoalWays = map[goal]!!

        return if (leftOrRight == LeftOrRight.LEFT) {
            Pair(nextGoalWays.first, acc.second + 1)
        } else {
            Pair(nextGoalWays.second, acc.second + 1)
        }
    }
}