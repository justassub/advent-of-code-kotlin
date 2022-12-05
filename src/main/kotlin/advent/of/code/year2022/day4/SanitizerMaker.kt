package advent.of.code.year2022.day4

import advent.of.code.year2021.day12.VillageParser

object SanitizerMaker {
    private val regex = Regex("""(.*?)-(.*?),(.*?)-(.*?)""")

    fun createPairSanitizers(line: String): Pair<SanitizerElf, SanitizerElf> {
        return regex.matchEntire(line)
            ?.destructured
            ?.let { (fromA, endA, fromB, endB) ->
                Pair(
                    SanitizerElf(fromA.toInt(), endA.toInt()),
                    SanitizerElf(fromB.toInt(), endB.toInt())
                )
            } ?: throw IllegalArgumentException("Line $line was incorrect")

    }
}