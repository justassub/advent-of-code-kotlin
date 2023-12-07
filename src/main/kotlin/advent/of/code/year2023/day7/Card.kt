package advent.of.code.year2023.day7



enum class Card(val value: Int) {
    JOKER(0), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8),
    NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14);
}

fun Char.getCardByLetter(): Card {
    return when (this) {
        '2' -> Card.TWO
        '3' -> Card.THREE
        '4' -> Card.FOUR
        '5' -> Card.FIVE
        '6' -> Card.SIX
        '7' -> Card.SEVEN
        '8' -> Card.EIGHT
        '9' -> Card.NINE
        'T' -> Card.TEN
        'J' -> Card.JACK
        'Q' -> Card.QUEEN
        'K' -> Card.KING
        'A' -> Card.ACE
        else -> throw IllegalArgumentException("Bad input $this")
    }
}

fun Char.getCardByLetterJoker(): Card {
    return when (this) {
        '2' -> Card.TWO
        '3' -> Card.THREE
        '4' -> Card.FOUR
        '5' -> Card.FIVE
        '6' -> Card.SIX
        '7' -> Card.SEVEN
        '8' -> Card.EIGHT
        '9' -> Card.NINE
        'T' -> Card.TEN
        'J' -> Card.JOKER
        'Q' -> Card.QUEEN
        'K' -> Card.KING
        'A' -> Card.ACE
        else -> throw IllegalArgumentException("Bad input $this")
    }
}