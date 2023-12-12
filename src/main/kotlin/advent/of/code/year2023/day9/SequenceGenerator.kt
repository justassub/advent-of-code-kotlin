package advent.of.code.year2023.day9

interface SequenceGenerator {
    fun isMatching(): Boolean;
    fun generateNext(): Int
    fun generateFirst(): Int
}


class ConstantNumberGenerator(private val numbers: List<Int>) : SequenceGenerator {
    override fun isMatching(): Boolean {
        return numbers.zipWithNext()
            .map { it.second - it.first }
            .distinct()
            .size == 1

    }

    override fun generateNext(): Int {
        return numbers.last() + (numbers[1] - numbers.first())
    }

    override fun generateFirst(): Int {
        return numbers.first() - (numbers[1] - numbers.first())
    }
}


class CustomNumbersGenerator(private val numbers: List<Int>) : SequenceGenerator {
    override fun isMatching() = true

    override fun generateNext(): Int {

        val constantNumberGenerator = ConstantNumberGenerator(numbers)
        return if (constantNumberGenerator.isMatching()) {
            constantNumberGenerator.generateNext()
        } else {
            val nextSequence = getNextNumberSequence()
            numbers.last() + CustomNumbersGenerator(nextSequence).generateNext()
        }
    }


    override fun generateFirst(): Int {
        val constantNumberGenerator = ConstantNumberGenerator(numbers)
        return if (constantNumberGenerator.isMatching()) {
            constantNumberGenerator.generateFirst()
        } else {
            val nextSequence = getNextNumberSequence()
            numbers.first() - CustomNumbersGenerator(nextSequence).generateFirst()
        }
    }

    private fun getNextNumberSequence(): List<Int> {
        return numbers
            .zipWithNext()
            .map { it.second - it.first }
    }
}