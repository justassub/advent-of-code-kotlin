package advent.of.code.year2022.day15

import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.math.min

sealed class SignalObject(open val x: Int, open val y: Int)

data class Beacon(override val x: Int, override val y: Int) : SignalObject(x, y)
data class NotBeacon(override val x: Int, override val y: Int) : SignalObject(x, y)

class Sensor(override val x: Int, override val y: Int, val beacon: Beacon) : SignalObject(x, y) {
    private val distanceBetweenSignal = calculateAbsoluteDistance()
    fun findAllExistingPointsInY(y: Int): Set<SignalObject> {
        val distanceBetweenSignal = calculateAbsoluteDistance()
        val diff = (y - this.y).absoluteValue


        val rangeBothSides = distanceBetweenSignal - diff
        return (x - rangeBothSides..x + rangeBothSides)
            .map { NotBeacon(it, y) }
            .toSet()
    }

    fun canCatch(x: Int, y: Int): Boolean {
        if (x == this.x && y == this.y) {
            return true
        }
        if (x == this.beacon.x && y == this.beacon.y) {
            return true
        }

        val diff = (y - this.y).absoluteValue

        val rangeBothSides = distanceBetweenSignal - diff

        return x >= (this.x - rangeBothSides) && x <= (this.x + rangeBothSides)
    }

    fun reducePossibleDisruptorSensor(range15: Range15): Range15 {
        val minX = x - distanceBetweenSignal - 1
        val maxX = x + distanceBetweenSignal + 1

        val minY = y - distanceBetweenSignal - 1
        val maxY = y + distanceBetweenSignal + 1



        return Range15(
            max(minX, range15.minX),
            min(maxX, range15.maxY),
            max(minY, range15.minY),
            min(maxY, range15.maxY)
        )

    }


    fun calculateAbsoluteDistance() = (x - beacon.x).absoluteValue + (y - beacon.y).absoluteValue

}
