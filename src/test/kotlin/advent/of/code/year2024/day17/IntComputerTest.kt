package advent.of.code.year2024.day17

import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals

class IntComputerTest {
    @Test
    fun `Should properly test 1`() {
        val registrarA = Registrar(1)
        val registrarB = Registrar(29)
        val registrarC = Registrar(7)

        val intComputer = IntComputer(registrarA, registrarB, registrarC)

        intComputer.runPrograms(LinkedList(listOf(1, 7)))

        assertEquals(26, registrarB.read())

    }

    @Test
    fun `Should properly test 2`() {
        val registrarA = Registrar(1)
        val registrarB = Registrar(29)
        val registrarC = Registrar(9)

        val intComputer = IntComputer(registrarA, registrarB, registrarC)

        intComputer.runPrograms(LinkedList(listOf(2, 6)))

        assertEquals(1, registrarB.read())
    }

    @Test
    fun `Should properly test 3`() {
        val registrarA = Registrar(729)
        val registrarB = Registrar(2024)
        val registrarC = Registrar(43690)

        val intComputer = IntComputer(registrarA, registrarB, registrarC)


        intComputer.runPrograms(LinkedList(listOf(4, 0)))

        assertEquals(44354, registrarB.read())
    }


    @Test
    fun `Should properly test 4`() {
        val registrarA = Registrar(10)
        val registrarB = Registrar(2024)
        val registrarC = Registrar(43690)

        val intComputer = IntComputer(registrarA, registrarB, registrarC)


        intComputer.runPrograms(listOf(5, 0, 5, 1, 5, 4))

        assertEquals(listOf(0, 1, 2), intComputer.output)
    }

    @Test
    fun `Should properly test 5`() {
        val registrarA = Registrar(2024)
        val registrarB = Registrar(3)
        val registrarC = Registrar(1)
        val intComputer = IntComputer(registrarA, registrarB, registrarC)


        intComputer.runPrograms(listOf(0, 1, 5, 4, 3, 0))

        assertEquals(listOf(4, 2, 5, 6, 7, 7, 7, 7, 3, 1, 0), intComputer.output)
    }

    @Test
    fun `Should properly test 6`() {
        val registrarA = Registrar(10)
        val registrarB = Registrar(1)
        val registrarC = Registrar(1)

        val intComputer = IntComputer(registrarA, registrarB, registrarC)

        intComputer.runPrograms(listOf(5, 0, 5, 1, 5, 4))

        assertEquals(listOf(0, 1, 2), intComputer.output)
    }
}