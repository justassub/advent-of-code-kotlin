package advent.of.code.year2023.day15

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals

class HashCalculatorTest {

    @ParameterizedTest
    @CsvSource(
        "0,HASH,52",
        "0,rn=1,30",
        "0,cm-,253",
        "0,qp=3,97"
    )
    fun `Calculate hash correctly`(initValue: Int, text: String, expectedValue: Int) {
        assertEquals(expectedValue, HashCalculatorDay15.calculateHash(initValue, text))
    }
}