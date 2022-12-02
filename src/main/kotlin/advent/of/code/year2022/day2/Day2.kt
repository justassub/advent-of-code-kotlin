package advent.of.code.year2022.day2

import advent.of.code.year2022.ContentReader2022

const val day = 2

fun findUser2Score(matches: List<Match>): Int {
    return matches.sumOf { ScoreCalculator.calculateMatchResult(it).player2Score }
}

fun main() {

    val originalLines = ContentReader2022.readFileAsLines(day)

    val data = originalLines.map { buildMatches(it) }
    println(findUser2Score(data))
    val data2 = originalLines.map { buildMatchesWithModification(it) }
    println(findUser2Score(data2))
}


private fun buildMatches(line: String): Match {
    val player1Sign = line.first()
    val player2Sign = line.last()

    return buildMatch(player1Sign, player2Sign)
}

private fun buildMatchesWithModification(line: String): Match {
    val player1Sign = line.first()
    val player2Sign = when (line.last()) {
        'Y' -> player1Sign
        'X' -> when (player1Sign) {
            'A' -> 'C'
            'B' -> 'A'
            'C' -> 'B'
            else -> throw SignNotFoundException("Wrong input: $player1Sign")
        }
        'Z' -> when (player1Sign) {
            'A' -> 'B'
            'B' -> 'C'
            'C' -> 'A'
            else -> throw SignNotFoundException("Wrong input: $player1Sign")
        }
        else -> throw SignNotFoundException("Wrong input: ${line.last()}")
    }

    return buildMatch(player1Sign, player2Sign)
}

private fun buildMatch(player1Sign: Char, player2Sign: Char): Match {
    return Match(
        action1 = Action(Player.ONE, toPlaySign(player1Sign)),
        response = Action(Player.TWO, toPlaySign(player2Sign))
    )
}


private fun toPlaySign(s: Char): PlaySign {

    return when (s) {
        'A', 'X' -> PlaySign.ROCK
        'B', 'Y' -> PlaySign.PAPER
        'C', 'Z' -> PlaySign.SCISSORS
        else -> throw SignNotFoundException("Wrong input: $s")
    }
}
