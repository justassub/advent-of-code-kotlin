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
}