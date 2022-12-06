package advent.of.code.year2022.day6

class ComSignal(private val text: String) {


    fun findNthUniqueAppearance(n: Int): Int {
        return text
            .windowed(n)
            .map { it.toSet() }
            .withIndex()
            .filter { it.value.size == n }
            .minOf { it.index } + n
    }

}
