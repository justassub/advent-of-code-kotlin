package advent.of.code.year2022.day15

object SignalObjectGenerator {
    private val regex = Regex("""Sensor at x=(.*?), y=(.*?): closest beacon is at x=(.*?), y=(.*?)""")

    fun buildSignal(line: String): Sensor {
        return regex.matchEntire(line)
            ?.destructured
            ?.let { (sX, sY, bX, bY) -> Sensor(sX.toInt(), sY.toInt(), Beacon(bX.toInt(), bY.toInt())) }
            ?: throw IllegalArgumentException("Cant construct bean and sensor: $line")
    }

}
