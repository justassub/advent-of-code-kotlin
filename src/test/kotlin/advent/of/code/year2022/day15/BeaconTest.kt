package advent.of.code.year2022.day15

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BeaconTest {

    @Test
    fun `Should calculate beacon top distance`() {
        val beacon = Beacon(2, 2)
        val sensor = Sensor(0, 0, beacon)
        Assertions.assertEquals(4, sensor.calculateAbsoluteDistance())
    }

    @Test
    fun `Should calculate beacon top distance2`() {
        val beacon = Beacon(-1, 2)
        val sensor = Sensor(1, -3, beacon)
        Assertions.assertEquals(7, sensor.calculateAbsoluteDistance())
    }

    @Test
    fun `Should find all beacon possible Distances1`() {
        val beacon = Beacon(2, 10)
        val sensor = Sensor(8, 7, beacon)

        Assertions.assertEquals(1, sensor.findAllExistingPointsInY(16).size)
        Assertions.assertEquals(5, sensor.findAllExistingPointsInY(14).size)
    }

    @Test
    fun `Should catch`() {
        val beacon = Beacon(2, 10)
        val sensor = Sensor(8, 7, beacon)

        Assertions.assertTrue(sensor.canCatch(8,16))
        Assertions.assertTrue(sensor.canCatch(9,15))
        Assertions.assertTrue(sensor.canCatch(7,15))
        Assertions.assertTrue(sensor.canCatch(8,-2))

        Assertions.assertTrue(sensor.canCatch(2,10))
        Assertions.assertTrue(sensor.canCatch(8,7))
    }

    @Test
    fun `Should not catch`() {
        val beacon = Beacon(2, 10)
        val sensor = Sensor(8, 7, beacon)


        Assertions.assertFalse(sensor.canCatch(7,16))
        Assertions.assertFalse(sensor.canCatch(9,16))
    }
}
