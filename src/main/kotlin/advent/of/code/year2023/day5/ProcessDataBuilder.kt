package advent.of.code.year2023.day5

object ProcessDataBuilder {
    fun createMultipleProcessData(lines: List<String>): List<ProcessData> {
        return Regex("""(.*?)-to-(.*?) map:""").matchEntire(lines.first())
            ?.destructured
            ?.let { (from, to) -> lines.drop(1).map { createProcessData(from, to, it) } }!!
    }

    private fun createProcessData(from: String, to: String, line: String): ProcessData {
        val params = line.split(" ")
            .map { it.trim() }
            .map { it.toLong() }

        return ProcessData(
            SeedLevel.valueOf(from.uppercase()),
            SeedLevel.valueOf(to.uppercase()),
            params[1],
            params[1] + params[2] - 1,
            params[0] - params[1]
        )
    }
}