package advent.of.code.year2023.day7

import advent.of.code.ContentReader

fun main() {


    calculateCardScore(::selectCardCombination)
    calculateCardScore(::calculateHandCombinationWithJoker) { c -> c.getCardByLetterJoker() }

}

private fun calculateCardScore(
    mapFunction: (input: List<Card>) -> CardCombination,
    cardMapFunction: (c: Char) -> Card = { it.getCardByLetter() }
) {
    ContentReader.readFileAsLines(2023, 7)
        .map { createBigAndDeck(it) }
        .map {

            val originalDeck = it.second
                .map(cardMapFunction)

            Hand(it.first.toInt(), mapFunction(originalDeck), originalDeck)
        }
        .sortedWith(sortingHandAlgo)
        .asReversed()
        .mapIndexed { index, hand -> hand.bid * (index + 1) }
        .sum()
        .run { println(this) }
}


fun createBigAndDeck(line: String): Pair<String, String> {
    val bid = line.split(" ").last()
    val deck = line.split(" ").first()
    return Pair(bid, deck)
}


private fun calculateHandCombinationWithJoker(deck: List<Card>): CardCombination {
    val nonJokers = deck.filterNot { it == Card.JOKER }
    val jokers = deck.size - nonJokers.size

    if (jokers == 0) {
        return selectCardCombination(deck)
    } else {
        val data = nonJokers
            .groupingBy { it }
            .eachCount()
        val maxRepeatedNonJokers = data.maxOfOrNull { it.value } ?: 0
        return when {
            maxRepeatedNonJokers + jokers > 4 -> CardCombination.FIVE
            maxRepeatedNonJokers + jokers > 3 -> CardCombination.FOUR
            maxRepeatedNonJokers == 1 && jokers == 1 -> CardCombination.PAIR
            maxRepeatedNonJokers == 1 && jokers == 2 -> CardCombination.THREE
            maxRepeatedNonJokers == 2 -> {
                val simpleResult = twoRepeatableCards(data)
                return if (simpleResult == CardCombination.TWO_PAIR) {
                    CardCombination.FULL_HOUSE
                } else {
                    CardCombination.THREE
                }
            }

            else -> throw IllegalArgumentException("Should not happen");
        }

    }

}

private fun selectCardCombination(originalDeck: List<Card>): CardCombination {
    val data = originalDeck
        .groupingBy { it }
        .eachCount()


    return when (data.maxOf { it.value }) {
        5 -> CardCombination.FIVE
        4 -> CardCombination.FOUR
        3 -> {
            if (data.containsValue(2)) {
                CardCombination.FULL_HOUSE
            } else {
                CardCombination.THREE
            }
        }

        2 -> twoRepeatableCards(data)

        1 -> CardCombination.HIGH

        else -> throw IllegalArgumentException("Should not happen")
    }

}

private fun twoRepeatableCards(
    data: Map<Card, Int>,
): CardCombination {
    val amount = data.count { it.value == 2 }
    return if (amount == 2) {
        CardCombination.TWO_PAIR
    } else {
        CardCombination.PAIR
    }
}
