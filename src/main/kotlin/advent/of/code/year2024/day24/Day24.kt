package advent.of.code.year2024.day24

import advent.of.code.ContentReader
import advent.of.code.util.equation.UnknownData
import advent.of.code.util.equation.findSolutions
import advent.of.code.util.printResult

data class UnknownDevice(
    val device1: String,
    val operation: DeviceOperation,
    val deviceTwo: String,
    val assignValueTo: String
)

fun createUnknownDevice(line: String): UnknownDevice {
    val regex = Regex("""(.*?) (.*?) (.*?) -> (.*?)""")
    return regex.matchEntire(line)
        ?.destructured
        ?.let { (device1, operation, deviceTwo, assignValueTo) ->
            UnknownDevice(device1, DeviceOperation.valueOf(operation), deviceTwo, assignValueTo)
        } ?: throw IllegalArgumentException("Invalid line $line")
}

fun main() {
    val deviceData = ContentReader.readFileAsLines(2024, 24)
        .map { it.split(": ") }
        .associate { it.first() to DeviceValue.fromValue(it[1].toInt()) }

    val unknowns = ContentReader.readFileAsLines(2024, 244)
        .map { createUnknownDevice(it) }
        .map { UnknownData(it.device1, it.deviceTwo, it.operation, it.assignValueTo) }


    val findSolutions = findSolutions(deviceData, unknowns) { d1, d2, operation ->
        operation.solve(d1, d2)
    }
    findSolutions
        .filter { it.key.startsWith("z") }
        .toSortedMap()
        .reversed()
        .map { it.value.value }
        .joinToString("").toLong(2)
        .printResult()
}

enum class DeviceValue(val value: Int) {
    ONE(1), ZERO(0);


    companion object {
        fun fromValue(int: Int): DeviceValue {
            return when (int) {
                1 -> ONE
                0 -> ZERO
                else -> throw IllegalArgumentException("Invalid value")
            }
        }
    }
}

enum class DeviceOperation {
    AND, OR, XOR;

    fun solve(d1: DeviceValue, d2: DeviceValue): DeviceValue {
        return when (this) {
            AND -> if (d1 == DeviceValue.ONE && d2 == DeviceValue.ONE) DeviceValue.ONE else DeviceValue.ZERO
            OR -> if (d1 == DeviceValue.ONE || d2 == DeviceValue.ONE) DeviceValue.ONE else DeviceValue.ZERO
            XOR -> if (d1 != d2) DeviceValue.ONE else DeviceValue.ZERO
        }
    }
}