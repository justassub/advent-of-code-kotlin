package advent.of.code.year2025.day2

import advent.of.code.ContentReader.readFileAsText

fun main() {
    val products = readFileAsText(2025, 2)
        .split(",")
        .map { it.trim().split("-") }
        .map { Product(it[0].toLong(), it[1].toLong()) }


    products
        .flatMap { p -> p.getIncorrectIds { isTextSameHalf(it) } }
        .let { println("Incorrect product IDs: ${it.sum()}") }


    products
        .flatMap { p -> p.getIncorrectIds { isNumberMadeFromSameNumbers(it) } }
        .let { println("Incorrect product IDs: ${it.sum()}") }


}

private fun isTextSameHalf(text: String): Boolean {
    val halfLength = text.length / 2
    val firstHalf = text.substring(0, halfLength)
    val secondHalf = text.substring(halfLength)
    return firstHalf == secondHalf
}

private fun isNumberMadeFromSameNumbers(number: String): Boolean {
    for (len in 1..number.length / 2) {
        if (number.length % len == 0) {
            val part = number.substring(0, len)
            if (part.repeat(number.length / len) == number) return true
        }
    }
    return false
}

private data class Product(
    val startRange: Long,
    val endRange: Long,
) {
    fun getIncorrectIds(filterFunc: (String) -> Boolean): List<Long> {
        return (startRange..endRange)
            .map { it.toString() }
            .filter { filterFunc(it) }
            .map { it.toLong() }
    }

}