package advent.of.code.year2021.day8

class Signal(private val digits: List<String>, private val output: List<String>) {
    private val values = mutableMapOf<Int, String>()

    val isLengthOf = { s: String, lenght: Int -> s.length == lenght }

    val patternOfOne = { s: String -> isLengthOf(s, 2) }
    val patternOfFour = { s: String -> isLengthOf(s, 4) }
    val patternOfSeven = { s: String -> isLengthOf(s, 3) }
    private val patternOfEight = { s: String -> isLengthOf(s, 7) }

    val simplePatters = listOf(patternOfOne, patternOfFour, patternOfSeven, patternOfEight)

    val patternOfThree = { s: String -> isLengthOf(s, 5) && s.toSet().containsAll(values.getValue(7).toSet()) }
    val patternOfFive = { s: String ->
        isLengthOf(s, 5) &&
                !patternOfThree(s) &&
                s.toSet().intersect(values.getValue(4).toSet()).size == 3
    }
    val patternOfTwo = { s: String ->
        isLengthOf(s, 5) && !patternOfThree(s) && !patternOfFive(s)
    }

    val patternOfNine = { s: String -> isLengthOf(s, 6) && s.toSet().containsAll(values.getValue(4).toSet()) }
    val patternOfZero =
        { s: String -> isLengthOf(s, 6) && !patternOfNine(s) && s.toSet().containsAll((values.getValue(7).toSet())) }
    val patternOfSix = { s: String -> isLengthOf(s, 6) && !patternOfNine(s) && !patternOfZero(s) }


    private fun findNumberByPatter(text: String): Int {
        return when {
            patternOfThree(text) -> 3
            patternOfFive(text) -> 5
            patternOfTwo(text) -> 2
            patternOfNine(text) -> 9
            patternOfZero(text) -> 0
            patternOfSix(text) -> 6
            else -> throw IllegalAccessException("Should not reach this ")
        }
    }

    private fun findIfSimpleNumbers(text: String): Pair<Int, String>? {
        return when {
            patternOfOne(text) -> Pair(1, text)
            patternOfSeven(text) -> Pair(7, text)
            patternOfFour(text) -> Pair(4, text)
            patternOfEight(text) -> Pair(8, text)
            else -> null
        }
    }


    fun calculateEasyValuesCount(): Int {
        return output.count { findIfSimpleNumbers(it) != null }
    }

    fun calculateOutputSum(): Int {
        digits.mapNotNull { findIfSimpleNumbers(it) }
            .forEach { values[it.first] = it.second }

        digits
            .filter { findIfSimpleNumbers(it) == null }
            .map { it.toNumberWithText() }
            .forEach { values[it.first] = it.second }
        return output
            .map { it.calculateAssociatedValue() }
            .joinToString("").toInt()

    }

    private fun String.toNumberWithText(): Pair<Int, String> = Pair(findNumberByPatter(this), this)

    private fun String.calculateAssociatedValue(): Int = values.filter { it.value == this }.map { it.key }.first()
}
