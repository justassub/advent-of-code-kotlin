package advent.of.code.year2022.day20

object NumberMixer {

    fun mixNumbers(numbers: List<Int>): List<Int> {
        val res = numbers.mapIndexed { i, n -> n to i }.toMutableList()
        numbers.indices.forEach {
            mixNumber(it, res)
        }
        return res.map { it.first }
    }

    private fun mixNumber(originalIndex: Int, allNumbers: MutableList<Pair<Int, Int>>) {
        val currentValue = allNumbers.first { it.second == originalIndex }
        val n = currentValue.first
        if (n == 0) {
            return
        }
        val currentPosition = allNumbers.indexOf(currentValue)

        allNumbers.removeAt(currentPosition)
        val nextPosition = (currentPosition + n).mod(allNumbers.size)
        allNumbers.add(nextPosition, n to originalIndex)

    }
}
