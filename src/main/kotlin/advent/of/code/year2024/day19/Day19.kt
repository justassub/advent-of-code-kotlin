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

    val designsCanBeCraftedInWays = designs.map { it.canBeCraftedWays() }

    designsCanBeCraftedInWays.count { it > 0 }
        .printResult()

    designsCanBeCraftedInWays.sum()
        .printResult()
}


private class Design(val towels: Set<Towel>, val originalMaterial: String) {
    companion object {
        val cache: MutableMap<String, Long> = mutableMapOf()
    }

    private fun findUsefulTowels(mat: String) = towels
        .filter { mat.startsWith(it) }

    fun canBeCraftedWays(): Long {
        return craftWays(originalMaterial)
    }

    private fun craftWays(leftMaterial: String): Long {
        if (cache.containsKey(leftMaterial)) {
            return cache[leftMaterial]!!
        }
        return cache[leftMaterial] ?: findUsefulTowels(leftMaterial)
            .map { leftMaterial.replaceFirst(it, "") }
            .sumOf {
                when {
                    it.isEmpty() -> 1L
                    else -> craftWays(it)
                }
            }
            .let {
                cache[leftMaterial] = it
                it
            }
    }
}