package advent.of.code.year2021

object ContentReader {
    fun readFileAsNumbers(day: Int): List<Int> {
        return ContentReader::class.java.getResource("/day$day.txt").readText()
            .lines()
            .map { it.toInt() }
    }
}