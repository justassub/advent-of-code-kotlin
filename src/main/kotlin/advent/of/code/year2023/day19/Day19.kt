package advent.of.code.year2023.day19

import advent.of.code.ContentReader
import advent.of.code.util.printResult


fun main() {
    val data = ContentReader.readFileAsLines(2023, 19)

    val rules = data.takeWhile { it.isNotEmpty() }
        .map { createRuleNameAndCondition(it) }
        .associate { it.first to createMultipleConditions(it.second) }


    val elements = data.drop(rules.size + 1)
        .map { it.replace("{", "").replace("}", "") }
        .map {
            it.split(",").map { e -> e.split("=") }
                .associate { elements -> elements.first().first() to elements.last().toInt() }
        }
    elements
        .filter { isMatching(rules, it) }
        .sumOf { it.values.sum() }
        .printResult()
}

private fun isMatching(
    rules: Map<String, List<Rule>>,
    elements: Map<Char, Int>
): Boolean {
    val firstRule = rules["in"]!!
    var nextStep = firstRule.firstNotNullOf { it.isMatching(elements) }

    while (nextStep != "A" && nextStep != "R") {
        nextStep = rules[nextStep]!!.firstNotNullOf { it.isMatching(elements) }
    }
    return nextStep == "A"
}


