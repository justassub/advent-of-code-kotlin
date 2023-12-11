package advent.of.code.year2023.day8

object MapConstructor {
    private val regex = Regex("""(.*?) = \((.*?), (.*?)\)""")


    fun createInitialMap(lines: List<String>): Map<String, Pair<String, String>> {
        return lines
            .associate { createPair(it) }
    }

    private fun createPair(line: String): Pair<String, Pair<String, String>> {

        return regex
            .matchEntire(line)
            ?.destructured
            ?.let { (name, element1, element2) -> name to Pair(element1, element2) }
            ?: throw IllegalArgumentException("Bad Data: $line")
    }
}