package advent.of.code.year2023.day1

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals

class CalibrationDocumentTest {


    @ParameterizedTest
    @CsvSource(
        "1abc2,12",
        "pqr3stu8vwx,38",
        "a1b2c3d4e5f,15",
        "treb7uchet,77"
    )
    fun findFirstAndLastNumbers(text: String, result: Int) {
        assertEquals(CalibrationDocument(text).calculateCalibrationValue(), result)
    }

    @ParameterizedTest
    @CsvSource(
        "4nineeightseven2,42",
        "eightwothree,83",
        "two1nine,29",
        "xtwone3four,24",
        "abcone2threexyz,13",
        "zoneight234,14",
        "7pqrstsixteen,76"
    )
    fun findFirstAndLastNumbersByText(text: String, result: Int) {
        assertEquals(CalibrationDocument(text).calculateFixedLetterValue(), result)
    }
}