package advent.of.code.year2022.day5

data class CrateCommand(val from: Int, val to: Int, val amount: Int)

object  CrateCommandGenerator {
    private val regex = Regex("""move (\d+) from (\d+) to (\d+)""")

    fun createCommand(line: String): CrateCommand {
        return regex
            .matchEntire(line)
            ?.destructured
            ?.let { (amount, from, to) -> CrateCommand(from.toInt(), to.toInt(), amount.toInt()) }
            ?: throw IllegalArgumentException("Bad Data")
    }
}
