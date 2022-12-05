package advent.of.code.year2022.day5

class CrateCommandRunner(
    private val createStacks: Map<Int, CrateStack>,
    private val commands: Collection<CrateCommand>
) {

    fun run(reverse: Boolean) {
        commands.forEach {
            var take = createStacks.getValue(it.from).retrieveLastNAndReverse(it.amount)
            if (reverse) {
                take = take.reversed()
            }
            createStacks.getValue(it.to).addNumberOfCratesOnTop(take)
        }
    }
}
