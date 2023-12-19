package advent.of.code.year2023.day19

interface Rule {
    fun isMatching(elements: Map<Char, Int>): String?
}

class SimpleRule(private val condition: String) : Rule {
    override fun isMatching(elements: Map<Char, Int>): String? {
        return condition
    }
}

class CustomRule(condition: String) : Rule {

    private val variableName = condition.first()
    private val variableSign = condition[1]
    private val variableRequirement = condition.drop(2).split(":").first().toInt()
    private val todoIfMatch = condition.split(":").last()

    override fun isMatching(elements: Map<Char, Int>): String? {
        if (variableSign == '>') {
            if (elements[variableName]!! > variableRequirement) {
                return todoIfMatch
            }
        } else {
            if (elements[variableName]!! < variableRequirement) {
                return todoIfMatch
            }
        }
        return null
    }
}







