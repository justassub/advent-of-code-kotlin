package advent.of.code.year2022

import advent.of.code.year2022.day7.CD
import advent.of.code.year2022.day7.LS
import advent.of.code.year2022.day7.UnixCommand
import advent.of.code.year2022.day7.UnixCommandGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UnixCommandGeneratorTest {
    @Test
    fun `Should create proper UNIX command CD`() {
        val commands = """
            $ cd /
        """.trimIndent()
            .lines()

        val result = UnixCommandGenerator.generateUnixCommands(commands)
        Assertions.assertEquals(1, result.size)
        Assertions.assertEquals(CD("/"), result.first())
    }
    @Test
    fun `Should create empty UNIX command LS`(){
        val commands = """
            $ cd /
            $ ls 
        """.trimIndent()
            .lines()

        val result = UnixCommandGenerator.generateUnixCommands(commands)
        Assertions.assertEquals(2, result.size)
        Assertions.assertEquals(CD("/"), result.first())
        Assertions.assertEquals(LS(emptyList()), result.last())
    }

    @Test
    fun `Should create proper UNIX commands LS and CD`(){
        val commands = """
            $ cd /
            $ ls 
            dir csjncqmr
            162385 dcgph
            $ cd csjncqmr
        """.trimIndent()
            .lines()

        val result = UnixCommandGenerator.generateUnixCommands(commands)
        Assertions.assertEquals(3, result.size)
        Assertions.assertEquals(CD("/"), result.first())
        Assertions.assertEquals(LS(listOf("dir csjncqmr","162385 dcgph")), result[1])
        Assertions.assertEquals(CD("csjncqmr"), result.last())
    }
}