package advent.of.code.year2022.day2

enum class PlaySign(val score: Int) {
    ROCK(1), PAPER(2), SCISSORS(3)
}

class SignNotFoundException(message: String) : RuntimeException(message)
