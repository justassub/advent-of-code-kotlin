package advent.of.code.year2021.day14

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PolymerBuilderTest {

    @ParameterizedTest
    @CsvSource(
        "CH -> B,CH,B",
        "CB -> H,CB,H"
    )
    fun `Should build Polymers correctly`(line: String, textExpected: String, charExpected: Char) {
        Assertions.assertEquals(
            PolymerTemplate(textExpected, charExpected),
            PolymerBuilder.buildPremiumPurchaseAggregate(line)
        )
    }
}
