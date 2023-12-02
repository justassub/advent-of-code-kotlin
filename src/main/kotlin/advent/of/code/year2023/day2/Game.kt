package advent.of.code.year2023.day2

data class Game(val id: Long, val sets: List<GameSet>)

data class GameSet(val subset: List<GameSubset>)

data class GameSubset(val colour: COLOUR, val amount: Int)

enum class COLOUR {
    RED, GREEN, BLUE
}


object GameBuilder {
    private val regex = Regex("""Game (.*?): (.*?)""")

    fun createGameFromLine(line: String): Game {
        return regex.matchEntire(line)
            ?.destructured
            ?.let { (gameId, subsetInfo) ->
                Game(gameId.toLong(), createGameSet(subsetInfo))
            } ?: throw IllegalArgumentException("Cant Game from line: $line")

    }

    private fun createGameSet(info: String): List<GameSet> {
        return info.split("; ")
            .map { GameSet(createGameSubset(it)) }
    }

    private fun createGameSubset(info: String): List<GameSubset> {
        return info.split(", ")
            .map { it.split(" ") }
            .map {
                GameSubset(
                    COLOUR.valueOf(it.last().uppercase()),
                    it.first().toInt()
                )
            }
    }
}

