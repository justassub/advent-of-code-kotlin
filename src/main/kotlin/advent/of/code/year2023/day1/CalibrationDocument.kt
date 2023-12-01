package advent.of.code.year2023.day1

class CalibrationDocument(private val document: String) {

    private val NUMBERS = listOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9
    )

    fun calculateCalibrationValue(): Int {
        val firstDigit = document.first { it.isDigit() }

        val lastDigit = document.last { it.isDigit() }
        return "$firstDigit$lastDigit".toInt()
    }

    fun calculateFixedLetterValue(): Int {
        val combinations = document.windowed(6, partialWindows = true)
        val firstDigit = combinations.firstNotNullOf(::matchesNumber)
        val lastDigit = combinations.asReversed().firstNotNullOf { matchesNumber(it, true) }
        return "$firstDigit$lastDigit".toInt()
    }

    private fun matchesNumber(text: String, searchLast: Boolean = false): Int? {
        val numbersToMatch = NUMBERS
            .filter { text.contains(it.first) }
            .map { it to text.indexOf(it.first) }

        val numberToMatch = numbersToMatch.minByOrNull { it.second }?.first

        val firstDigit = text.firstOrNull { it.isDigit() }
        return when {
            numberToMatch == null -> firstDigit?.digitToInt()
            firstDigit == null -> numberToMatch.second
            else -> {
                val textIndex = text.indexOf(numberToMatch.first)
                val numberIndex = text.indexOf(firstDigit)

                return if (textIndex < numberIndex) {
                    if (!searchLast) {
                        numberToMatch.second
                    } else {
                        firstDigit.digitToInt()
                    }

                } else {
                    if (!searchLast) {
                        firstDigit.digitToInt()
                    } else {
                        numberToMatch.second
                    }
                }
            }
        }
    }
}