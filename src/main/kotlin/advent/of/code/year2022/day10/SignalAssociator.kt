package advent.of.code.year2022.day10

object SignalActionAssociator {

    fun associateSignalActionsWithCycles(actions: Collection<SignalAction>): List<Pair<Int, SignalAction>> {
        var index = 0;
        return actions.map {
            index += it.signalType.requiredCycles
            Pair(index, it)
        }
    }
}