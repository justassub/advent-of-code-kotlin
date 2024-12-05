package advent.of.code.year2024.day5

import advent.of.code.ContentReader
import advent.of.code.util.printResult

fun main() {
    val rules = ContentReader.readFileAsMultipleNumbersInLine(2024, 5, "|")
        .map { it[0] to it[1] }
        .toSet()


    val pages = ContentReader.readFileAsMultipleNumbersInLine(2024, 55, ",")

    pages.filter { isPageValid(it, rules) }
        .sumOf { it[it.size / 2] }
        .printResult()


    pages.filterNot { isPageValid(it, rules) }
        .map { remakePage(it, rules) }
        .sumOf { it[it.size / 2] }
        .printResult()
}

fun remakePage(page: List<Int>, rules: Set<Pair<Int, Int>>): List<Int> {
    val rulesToFollow = findRulesByPage(page, rules)
    val newList = mutableListOf<Int>()

    while (newList.size != page.size) {
        val affectingNumbers = rulesToFollow
            .filterNot { newList.contains(it.first) }
            .map { it.second }
            .toSet()

        val numberCanBeAdded = page
            .filterNot { newList.contains(it) }
            .filterNot { affectingNumbers.contains(it) }
            .first()

        newList.add(numberCanBeAdded)
    }
    return newList
}

fun isPageValid(page: List<Int>, rules: Set<Pair<Int, Int>>): Boolean {
    return findInvalidRules(page, rules).isEmpty()
}


fun findInvalidRules(page: List<Int>, rules: Set<Pair<Int, Int>>): Set<Pair<Int, Int>> {
    return rules.filter { page.contains(it.first) && page.contains(it.second) }
        .filterNot {
            page.indexOf(it.first) < page.indexOf(it.second)
        }.toSet()
}

private fun findRulesByPage(
    page: List<Int>,
    rules: Set<Pair<Int, Int>>,
) = rules.filter { page.contains(it.first) && page.contains(it.second) }