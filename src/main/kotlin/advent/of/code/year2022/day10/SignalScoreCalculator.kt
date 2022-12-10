package advent.of.code.year2022.day10

class SignalScoreCalculator(private val actions: List<Pair<Int, SignalAction>>) {

    fun calculateScoreOfCycles(cyclesCount: Int): List<Pair<Int, Int>> {
        return associateScoreWithCycles(0, cyclesCount)

    }

    private fun associateScoreWithCycles(startCycle: Int = 0, cyclesCount: Int): List<Pair<Int, Int>> {
        return actions
            .takeWhile { it.first in startCycle until cyclesCount }
            .runningFold(Pair(0, 1)) { acc, pair -> Pair(pair.first, acc.second + pair.second.value) }
    }

}