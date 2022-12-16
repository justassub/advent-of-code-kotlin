package advent.of.code.year2022.day16

object ValveGenerator {
    private val regex = Regex("""Valve (.*?) has flow rate=(.*?); tunnel leads to valve (.*?)""")
    fun generateValves(lines: List<String>): Map<String, Valve> {
        return lines
            .map { it.replace("valves", "valve") }
            .map { it.replace("tunnels lead", "tunnel leads") }
            .map { generateValve(it) }
            .associateBy { it.name }
    }

    private fun generateValve(line: String): Valve {
        return regex.matchEntire(line)
            ?.destructured
            ?.let { (valveName, flowRate, closeValves) ->

                Valve(
                    valveName, flowRate.toInt(), closeValves.split(",").map { it.trim() }.toSet()
                )
            } ?: throw IllegalArgumentException("Line $line was incorrect")
    }
}
