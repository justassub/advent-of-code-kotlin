package advent.of.code.year2021.day14

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PolymerTest {
    @ParameterizedTest
    @CsvSource(
        "NN,C,NCN",
        "BDBD,P,BDPBD",
        "XA,A,XAA",
        "RP,I,RIP"
    )
    fun `Should merge Polymers correctly`(poly: String, char: Char, expected: String) {
        Assertions.assertEquals(expected, PolymerTemplate(poly, char).mergePolymer())
    }
}
