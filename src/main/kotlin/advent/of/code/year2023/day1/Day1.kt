package advent.of.code.year2023.day1

import advent.of.code.ContentReader


fun main() {
    val data = ContentReader.readFileAsLines(2023, 1)

    val calibrationDocuments = data
        .map { CalibrationDocument(it) }
    calibrationDocuments
        .sumOf { it.calculateCalibrationValue() }
        .run { println(this) }

    calibrationDocuments
        .sumOf { it.calculateFixedLetterValue() }
        .run { println(this) }

}