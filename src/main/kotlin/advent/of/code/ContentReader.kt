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

    fun readAsGroupOfLinesSplitByEmptyLine(year: Int, day: Int): List<List<String>> {
        return readFileAsText(year, day)
            .trim()
            .split("\n\n")
            .map { it.trim().split("\n") }
    }

    fun readFileWithSeparator(year: Int, day: Int, separator: String): List<List<String>> {
        return readFileAsLines(year, day)
            .map { it.split(separator) }
    }
    fun readFileAsMultipleNumbersInLine(year: Int, day: Int, separator: String):List<List<Int>>{
        return readFileWithSeparator(year, day, separator)
            .map { it.map { it.toInt() } }
    }
}
