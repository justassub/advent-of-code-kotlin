package advent.of.code.year2021.day10

import advent.of.code.year2021.ContentReader


class Day10 {
    private val chunkCorruptedValues = mapOf(
        ')' to 3,
        ']' to 57,
        '}' to 1197,
        '>' to 25137
    )

    private val chunkMissingValues = mapOf(
        ')' to 1,
        ']' to 2,
        '}' to 3,
        '>' to 4
    )

    fun part1(chunks: List<Chunk>): Long {
        return chunks
            .map { ChunkValidator.validate(it) }
            .filter { it.first == ChunkType.CORRUPTED }
            .sumOf { chunkCorruptedValues[it.second.first()]!!.toLong() }
    }

    fun part2(chunks: List<Chunk>): Long {
        val sortedIncompleteChunksByScore = chunks
            .map { ChunkValidator.validate(it) }
            .filter { it.first == ChunkType.INCOMPLETE }
            .map { calculateScore(it.second) }
            .sortedDescending()
        return sortedIncompleteChunksByScore[sortedIncompleteChunksByScore.size / 2]
    }

    private fun calculateScore(missingChunks: Collection<Char>): Long {
        return missingChunks.fold(0L) { acc, i -> acc * 5 + chunkMissingValues[i]!! }
    }
}

fun main() {
    val data = ContentReader.readFileAsLines(10)
        .filter { it.isNotEmpty() }
    val day10 = Day10()
    println(day10.part1(data))
    println(day10.part2(data))
}
