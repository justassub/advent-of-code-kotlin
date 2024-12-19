package advent.of.code.year2024.day19

import advent.of.code.ContentReader
import advent.of.code.util.printResult

private typealias Towel = String

fun main() {

    val towels = ContentReader.readFileAsLines(2024, 19)
        .flatMap { it.split(", ") }
        .toSet()

    val designs = ContentReader.readFileAsLines(2024, 199)
        .map { Design(towels, it) }

    designs.count { it.canBeCrafted() }
        .printResult()
}


private class Design(val towels: Set<Towel>, val originalMaterial: String) {

    private fun findUsefulTowels(mat: String) = towels
        .filter { mat.contains(it) }

    fun canBeCrafted(): Boolean {
        return craft(originalMaterial)
    }

    private fun craft(leftMaterial: String): Boolean {
        val towelsCanBeUsed = findUsefulTowels(leftMaterial)
        return towelsCanBeUsed
            .map { leftMaterial.replaceFirst(it, "") }
            .any {
                it.isEmpty() || craft(it)
            }
    }
}