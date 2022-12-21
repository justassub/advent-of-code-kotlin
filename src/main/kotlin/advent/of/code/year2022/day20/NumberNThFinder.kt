package advent.of.code.year2022.day20

class NumberNThFinder(private val numbers: List<Int>) {

    fun findRepeatNthNumber(number: Int): Int {
        val indexOfZero = numbers.indexOf(0)
        val numberIndex = (number - (numbers.size - 1 - indexOfZero)) % numbers.size
        return numbers.get(numberIndex - 1)

    }
}
