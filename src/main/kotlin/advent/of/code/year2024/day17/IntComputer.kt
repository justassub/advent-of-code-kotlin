package advent.of.code.year2024.day17

import kotlin.math.pow

class IntComputer(
    private val registrarA: Registrar,
    private val registrarB: Registrar,
    private val registrarC: Registrar
) {
    val output = mutableListOf<Int>()
    private var instructionPointer = 0

    fun runPrograms(commands: List<Int>) {
        while (instructionPointer < commands.size - 1) {
            val command = commands[instructionPointer].toLong()
            val digit = commands[instructionPointer + 1].toLong()
            activateInstructionWithValue(command, digit)
        }
    }


    private fun activateInstructionWithValue(digit: Long, value: Long) {
        when (digit) {
            1L -> registrarB.register(registrarB.read() xor value)
            2L -> registrarB.register(value.createComboOperator() % 8)
            3L -> {
                val aValue = registrarA.read()
                if (aValue != 0L) {
                    instructionPointer = value.toInt()
                    return
                }
            }

            4L -> registrarB.register(
                registrarB.read() xor registrarC.read()
            )


            5L -> output.add((value.createComboOperator() % 8).toInt())

            0L, 6L, 7L -> {
                val numerator = registrarA.read()
                val divider = 2.0.pow(value.createComboOperator().toDouble()).toLong()
                val result = (numerator / divider)
                when (digit) {
                    0L -> registrarA.register(result)
                    6L -> registrarB.register(result)
                    7L -> registrarC.register(result)
                }
            }

            else -> throw IllegalArgumentException("Unknown command")
        }
        instructionPointer += 2
    }


    private fun Long.createComboOperator(): Long {
        return when (this) {
            0L, 1L, 2L, 3L -> this
            4L -> registrarA.read()
            5L -> registrarB.read()
            6L -> registrarC.read()
            else -> throw IllegalArgumentException("Unknown command $this")
        }
    }
}


data class Registrar(var d: Long) {
    fun register(d: Long) {
        this.d = d
    }

    fun read(): Long {
        return d
    }
}