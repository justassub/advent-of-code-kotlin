package advent.of.code.year2021.day14

data class PolymerTemplate(val condition: String,  val insertion: Char) {
    private val conditionSize = condition.length
    fun matchCondition(string: String) = string == condition

    fun mergePolymer() =
        condition.substring(0, conditionSize / 2) + insertion + condition.substring(conditionSize / 2)
}
