package advent.of.code.year2022.day6

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ComSignalTest {


    @Test
    fun `Should find nppdvjthqldpwncqszvftbrmjlhg elements`() {
        var signal = ComSignal("nppdvjthqldpwncqszvftbrmjlhg")
        assertEquals(6, signal.findNthUniqueAppearance(4))
        assertEquals(23, signal.findNthUniqueAppearance(14))
    }


    @Test
    fun `Should find bvwbjplbgvbhsrlpgdmjqwftvncz elements`() {
        var signal = ComSignal("bvwbjplbgvbhsrlpgdmjqwftvncz")
        assertEquals(5, signal.findNthUniqueAppearance(4))
        assertEquals(23, signal.findNthUniqueAppearance(14))

    }


    @Test
    fun `Should find nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg elements`() {
        var signal = ComSignal("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")
        assertEquals(10, signal.findNthUniqueAppearance(4))
        assertEquals(29, signal.findNthUniqueAppearance(14))

    }

    @Test
    fun `Should find zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw elements`() {
        var signal = ComSignal("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")
        assertEquals(11, signal.findNthUniqueAppearance(4))
        assertEquals(26, signal.findNthUniqueAppearance(14))

    }

}
