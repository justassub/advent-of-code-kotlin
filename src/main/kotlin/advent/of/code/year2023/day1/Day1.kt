package advent.of.code.year2023.day1

import advent.of.code.ContentReader

const val day = 1


fun main() {
    val data = ContentReader.readFileAsLines(2023, 1)

    data
        .map { CalibrationDocument(it) }
        .sumOf { it.calculateCalibrationValue() }
        .run { println(this) }

}