package advent.of.code.year2021.day10

typealias Chunk = String

object ChunkValidator {


    private val closingWithOpening = mapOf(
        ')' to '(',
        ']' to '[',
        '}' to '{',
        '>' to '<'
    )
    private val openingWithClosing = closingWithOpening.entries.associate { (k, v) -> v to k }

    fun validate(chunk: Chunk): Pair<ChunkType, Collection<Char>> {
        val symbols = ArrayDeque<Char>()
        symbols.add(chunk[0])
        for (i in 1 until chunk.length) {
            val nextSymbol = chunk[i]
            if (closingWithOpening.containsKey(nextSymbol)) {
                val previous = symbols.removeLast()
                if (previous != closingWithOpening[nextSymbol]) {
                    return Pair(ChunkType.CORRUPTED, listOf(nextSymbol))
                }
            } else {
                symbols.add(nextSymbol)
            }
        }
        symbols.reverse()
        return Pair(ChunkType.INCOMPLETE, symbols.map { openingWithClosing[it]!! })
    }

}
