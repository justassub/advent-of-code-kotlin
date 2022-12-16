package advent.of.code.year2022.day15

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SignalObjectGeneratorTest {
    @Test
    fun `Should create proper Sensor with beacon`() {
        val sensor = SignalObjectGenerator.buildSignal("Sensor at x=2, y=18: closest beacon is at x=-2, y=15")
        Assertions.assertEquals(2, sensor.x)
        Assertions.assertEquals(18, sensor.y)
        Assertions.assertEquals(Beacon(-2, 15), sensor.beacon)
    }
}
