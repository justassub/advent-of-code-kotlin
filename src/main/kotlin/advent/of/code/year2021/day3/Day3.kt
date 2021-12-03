package advent.of.code.year2021.day3

import advent.of.code.year2021.ContentReader

class Day3 {
    fun part1(data: List<String>): Long {
        val length = data.first().length - 1
        val candidates = data.map { OxygenCandidate(it) }
        val gamma = (0..length)
            .map { findDominatingSymbol(it, candidates) }
            .joinToString("")

        val epsilon = (0..length)
            .map { findSubmissiveSymbol(it, candidates) }
            .joinToString("")

        println(gamma)
        println(epsilon)
        return gamma.toLong(2) * epsilon.toLong(2)
    }

    fun part2(data: List<String>): Long {
        val candidatesForOxygen = data.map { OxygenCandidate(it) }
        val candidatesForScrubber = data.map { OxygenCandidate(it) }
        val oxygen = findRating(candidatesForOxygen) { index -> findDominatingSymbol(index, candidatesForOxygen) }
        val scrubber = findRating(candidatesForScrubber) { index -> findSubmissiveSymbol(index, candidatesForScrubber) }
        println(oxygen)
        println(scrubber)
        return oxygen.toLong(2) * scrubber.toLong(2)
    }

    fun findRating(candidates: List<OxygenCandidate>, symbolFindFunc: (s: Int) -> Char): String {
        assert(candidates.isNotEmpty()) { "Candidates can not be null" }
        var position = 0
        while (!foundOxygenGeneratorRating(candidates)) {
            val dominatingSymbol = symbolFindFunc(position)
            candidates
                .filter { it.number[position] != dominatingSymbol }
                .forEach { it.isValid = false }
            position++
        }
        return findValidCandidates(candidates).first().number
    }

    fun findDominatingSymbol(position: Int, candidates: List<OxygenCandidate>): Char {
        val groupedChars = groupCharsByCountAtPosition(position, candidates)
        val zerosCount = groupedChars['0']!!
        val onesCount = groupedChars['1']!!
        return if (zerosCount > onesCount) '0' else '1'
    }

    fun findSubmissiveSymbol(position: Int, candidates: List<OxygenCandidate>): Char {
        val groupedChars = groupCharsByCountAtPosition(position, candidates)
        val zerosCount = groupedChars['0']!!
        val onesCount = groupedChars['1']!!
        return if (zerosCount <= onesCount) '0' else '1'
    }

    fun groupCharsByCountAtPosition(position: Int, candidates: List<OxygenCandidate>): Map<Char, Int> {
        return findValidCandidates(candidates)
            .map { it.number[position] }
            .groupingBy { it }
            .eachCount()
    }

    private fun foundOxygenGeneratorRating(candidates: List<OxygenCandidate>): Boolean {
        return findValidCandidates(candidates).size == 1
    }

    private fun findValidCandidates(candidates: List<OxygenCandidate>): List<OxygenCandidate> {
        return candidates
            .filter { it.isValid }
    }
}

class OxygenCandidate(val number: String) {
    var isValid: Boolean = true
}

fun main() {
    val day3Part2 = Day3()
    val data = ContentReader.readFileAsLines(3)

    val result2 = day3Part2.part2(data)
    println(result2)

}