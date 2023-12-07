package advent.of.code.year2023.day7

enum class CardCombination(val value: Int) {
    FIVE(0),
    FOUR(1),
    FULL_HOUSE(2),
    THREE(3),
    TWO_PAIR(4),
    PAIR(5),
    HIGH(6)
}