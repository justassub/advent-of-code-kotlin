package advent.of.code.year2022.day2

object ScoreCalculator {

    fun calculateMatchResult(match: Match): MatchScore {

        val winner = findWinner(match.action1.playSign, match.response.playSign)

        fun findDefaultScore(player: Player): Int {
            return when (winner) {
                null -> 3
                player -> 6
                else -> 0
            }
        }

        val player1Score = findDefaultScore(match.action1.player) + match.action1.playSign.score
        val player2Score = findDefaultScore(match.response.player) + match.response.playSign.score

        return MatchScore(
            player1Score, player2Score
        )

    }

    private fun findWinner(player1Sign: PlaySign, player2Sign: PlaySign): Player? {
        return when (player1Sign) {
            player2Sign -> null
            else -> when (player1Sign) {
                PlaySign.ROCK -> when (player2Sign) {
                    PlaySign.SCISSORS -> Player.ONE
                    else -> Player.TWO
                }
                PlaySign.PAPER -> when (player2Sign) {
                    PlaySign.ROCK -> Player.ONE
                    else -> Player.TWO
                }
                PlaySign.SCISSORS -> when (player2Sign) {
                    PlaySign.PAPER -> Player.ONE
                    else -> Player.TWO
                }
            }
        }
    }
}
