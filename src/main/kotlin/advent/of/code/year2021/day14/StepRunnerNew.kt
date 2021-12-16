package advent.of.code.year2021.day14

class StepRunnerNew(private val pairs: Map<String, Long>, private val polymerRules: Set<PolymerTemplate>) {
    private fun findRuleBy(t: String) = polymerRules.first { it.matchCondition(t) }

    fun runAllRules(): Map<String, Long> {
        val newPairs = mutableMapOf<String, Long>()
        pairs.forEach { (p, count) ->
            run {
                val rule = findRuleBy(p)
                val s1 = rule.mergePolymer().dropLast(1)
                val s2 = rule.mergePolymer().drop(1)
                newPairs.merge(s1, count) { c1, c2 -> c1 + c2 }
                newPairs.merge(s2, count) { c1, c2 -> c1 + c2 }
            }
        }
        return newPairs
    }
}
