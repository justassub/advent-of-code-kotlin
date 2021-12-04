package advent.of.code.year2021.day4

class Row(val numbers: Set<Int>) {
    private val markedNumbers = mutableSetOf<Int>()

    fun addNumberAndSayIfWon(number: Int): Boolean {
        if (numbers.contains(number)) {
            markedNumbers.add(number)
        }
        return markedNumbers.size == numbers.size
    }

    fun getUnmarkedNumbers(): Set<Int> {
        return numbers.minus(markedNumbers)
    }
}