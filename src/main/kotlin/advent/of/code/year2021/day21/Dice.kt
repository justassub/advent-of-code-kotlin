package advent.of.code.year2021.day21

class Dice {
    var rolls = 0
    var nextScore = 1

    fun rollTimes(timesToRoll: Int): Int {
        return (0 until timesToRoll).sumOf { roll() }
    }

    private fun roll(): Int {
        val score = nextScore
        rolls = rolls.inc()
        nextScore = if (score == 100) {
            1
        } else {
            nextScore.inc()
        }
        return score
    }
}
