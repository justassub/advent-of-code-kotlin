package advent.of.code.year2022.day3

object PriorityCalculator {
    private val lowerCaseValues = ('a'..'z').mapIndexed { index: Int, c: Char -> c to index + 1 }.toMap()
    private val upperCaseValues = ('A'..'Z').mapIndexed { index: Int, c: Char -> c to index + 27 }.toMap()

    fun funCalculatePriority(letters: Collection<Char>): Int {
        return letters.sumOf { calculateChar(it) }
    }

    private fun calculateChar(char: Char): Int {
        return when (char.isUpperCase()) {
            true -> calculateUpperCase(char)
            false -> calculateLowerCase(char)
        }
    }

    private fun calculateUpperCase(char: Char): Int {
        return upperCaseValues.getValue(char)
    }

    private fun calculateLowerCase(char: Char): Int {
        return lowerCaseValues.getValue(char)
    }
}
