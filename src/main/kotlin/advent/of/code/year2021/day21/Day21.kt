package advent.of.code.year2021.day21

import advent.of.code.year2021.ContentReader



class Day21 {
    fun task1(playersInfo: List<String>): Int {
        val dice = Dice()
        val players = playersInfo
            .filter { it.isNotEmpty() }
            .map { PlayerBuilder.buildPlayer(it) }

        playWhileAtLeastOneWins(dice, players)
        val loser = players.first { !it.alreadyWon() }

        return loser.score * dice.rolls

    }


    private fun playWhileAtLeastOneWins(dice: Dice, players: List<Player>) {
        while (true) {
            players.forEach {
                val moveBy = dice.rollTimes(3)
                it.move(moveBy)
                if (it.alreadyWon()) {
                    return
                }
            }
        }
    }
}


object PlayerBuilder {
    private val regex = Regex("""Player (.*?) starting position: (.*?)""")

    fun buildPlayer(line: String): Player {
        return regex.matchEntire(line)
            ?.destructured
            ?.let { (_, starting) -> Player(startingPosition = starting.toInt()) }
            ?: throw IllegalArgumentException("Cant construct player from line: $line")
    }
}

fun main() {
    val lines = ContentReader.readFileAsLines(21)
    val day21 = Day21()

    println(day21.task1(lines))

}
