package advent.of.code.year2022.day5

object OriginalCrateStackGenerator {
    fun generateOriginalStacks(lines: List<String>): Map<Int, CrateStack> {
        val result = lines
            .map { associateCrateWithStack(it) }
        val stacks = result
            .maxBy { it.size }
            .keys
            .associateWith { CrateStack() }

        result.reversed().forEach { command ->
            command.forEach {
                stacks.getValue(it.key).addSingleCrateOnTop(it.value)
            }
        }

        return stacks
    }

    private fun associateCrateWithStack(line: String): Map<Int, Crate> {
        return (1..line.length - 2 step 4)
            .mapIndexed { index, i -> Pair(index + 1, line[i]) }
            .filter { it.second.isLetter() }
            .toMap()
    }
}
