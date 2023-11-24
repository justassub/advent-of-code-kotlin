package advent.of.code

object ContentReader {
    fun readFileAsNumbers(year: Int, day: Int): List<Int> {
        return readFileAsLines(year, day)
            .map { it.toInt() }
    }

    fun readFileAsLines(year: Int, day: Int): List<String> {
        return ContentReader::class.java.getResource("/year$year/day$day.txt").readText()
            .trim()
            .lines()
    }

    fun readFileAsText(year: Int, day: Int): String {
        return ContentReader::class.java.getResource("/year$year/day$day.txt")
            .readText(Charsets.UTF_8)
    }
}
