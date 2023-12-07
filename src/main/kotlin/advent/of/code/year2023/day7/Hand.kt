package advent.of.code.year2023.day7

data class Hand(
    val bid: Int,
    val cardCombination: CardCombination,
    val hands: List<Card>
)

val sortingHandAlgo = Comparator<Hand> { a, b ->
    when {
        a.cardCombination != b.cardCombination -> a.cardCombination.value - b.cardCombination.value
        else -> compareCardByCard(a.hands, b.hands)
    }
}

fun compareCardByCard(handsA: List<Card>, handsB: List<Card>): Int {
    return handsA.zip(handsB)
        .firstOrNull { it.first != it.second }
        ?.let { compareValues(it.second.value, it.first.value) }
        ?: 0
}
