package advent.of.code.year2022.day20

object NumberMixer {

    fun mixNumbers(numbers: List<Int>): List<Int> {
        val res = numbers.toMutableList()
        numbers.asSequence().forEach {
            mixNumber(it, res)
        }
        return res
    }

    private fun mixNumber(n: Int, allNumbers: MutableList<Int>) {
        if (n == 0) {
            return
        }
        val currentPosition = allNumbers.indexOf(n)
        val totalSize = allNumbers.size

        allNumbers.removeAt(currentPosition)
        val nextPosition = currentPosition + n

        when {
            nextPosition < 0 -> {
                val nextIndex = totalSize + nextPosition - 1
                allNumbers.add(nextIndex, n)
            }

            nextPosition > totalSize -> {
                val nextIndex = nextPosition - totalSize + 1
                allNumbers.add(nextIndex, n)

            }

            nextPosition == 0 -> allNumbers.add(totalSize - 1, n)

            nextPosition == totalSize - 1 -> allNumbers.add(0, n)
            else -> allNumbers.add(nextPosition, n)
        }
    }
}
