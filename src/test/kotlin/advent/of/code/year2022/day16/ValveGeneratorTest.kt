package advent.of.code.year2022.day16

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ValveGeneratorTest {

    @Test
    fun `Should parse Valve 1`() {
        val lines = """
            Valve AA has flow rate=0; tunnels lead to valves DD, II, BB
        """.trimIndent().lines()

        val result = ValveGenerator.generateValves(lines)
        Assertions.assertEquals(1, result.size)
        Assertions.assertEquals(Valve("AA", 0, setOf("DD", "II", "BB")), result["AA"])
    }
    @Test
    fun `Should parse Valve 2`() {
        val lines = """
            Valve HH has flow rate=22; tunnel leads to valve GG
        """.trimIndent().lines()

        val result = ValveGenerator.generateValves(lines)
        Assertions.assertEquals(1, result.size)
        Assertions.assertEquals(Valve("HH", 22, setOf("GG")), result["HH"])
    }
}
