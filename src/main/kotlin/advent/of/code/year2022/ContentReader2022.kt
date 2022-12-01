package advent.of.code.year2022

object ContentReader2022 {
    fun readFileAsNumbers(day: Int): List<Int> {
        return readFileAsLines(day)
            .map { it.toInt() }
    }

    fun readFileAsLines(day: Int): List<String> {
        return ContentReader2022::class.java.getResource("/year2022/day$day.txt").readText()
            .trim()
            .lines()
    }

    fun readFileAsText(day: Int): String {
        return ContentReader2022::class.java.getResource("/year2022/day$day.txt")
            .readText(Charsets.UTF_8)
    }
}
