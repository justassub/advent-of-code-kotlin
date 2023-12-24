package advent.of.code.year2023.day24

import advent.of.code.util.BigPoint

class Hailstone(val start: BigPoint, val deltaX: Long, val deltaY: Long) {

    val slope: Double = 1.0 * deltaY / deltaX

    //y=slope*x + B
    val b = start.y - slope * start.x
}