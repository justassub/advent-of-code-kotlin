package advent.of.code.year2021

object ContentReader {
    fun readFileAsNumbers(day: Int): List<Int> {
        return readFileAsLines(day)
            .map { it.toInt() }
    }

    fun readFileAsLines(day: Int): List<String> {
        return ContentReader::class.java.getResource("/day$day.txt").readText()
            .trim()
            .lines()
    }

    fun readFileAsText(day: Int): String {
        return ContentReader::class.java.getResource("/day$day.txt")
            .readText(Charsets.UTF_8)
    }
}
