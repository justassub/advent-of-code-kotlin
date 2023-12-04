package advent.of.code.year2023.day4

class Scratchcard(
    private val id: Int,
    private val numbers: List<Int>,
    private val winningNumbers: List<Int>
) {

    fun calculateWinningNumbers() = numbers.filter { winningNumbers.contains(it) }
}