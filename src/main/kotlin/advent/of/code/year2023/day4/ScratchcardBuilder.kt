package advent.of.code.year2023.day4

object ScratchcardBuilder {
    private val regex = Regex("""Card (.*?): (.*?)""")

    fun createGameFromLine(line: String): Scratchcard {
        return regex.matchEntire(line)
            ?.destructured
            ?.let { (id, allNumbers) ->
                val numbers = allNumbers.split(" | ")[0].split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
                val winningNumbers = allNumbers.split(" | ")[1].split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
                Scratchcard(
                    id = id.trim().toInt(),
                    numbers = numbers,
                    winningNumbers = winningNumbers
                )
            } ?: throw IllegalArgumentException("Cant Scratchcard from line: $line")

    }

}