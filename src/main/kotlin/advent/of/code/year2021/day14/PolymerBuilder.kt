package advent.of.code.year2021.day14

object PolymerBuilder {

    fun buildPremiumPurchaseAggregate(line: String): PolymerTemplate {
        return PolymerTemplate(line.substringBefore(" -> "), line.substringAfter(" -> ").first())
    }
}
