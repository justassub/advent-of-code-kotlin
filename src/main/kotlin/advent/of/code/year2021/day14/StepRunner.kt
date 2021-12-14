package advent.of.code.year2021.day14

class StepRunner(private val initialString: String, val polymerRules: Set<PolymerTemplate>) {
    private fun findRuleBy(t: String) = polymerRules.first { it.matchCondition(t) }

    fun runAllRules(string: String = initialString): String {
        return string.windowed(2).joinToString("") {
            findRuleBy(it).mergePolymer().drop(1)
        }
    }
}
