package advent.of.code.year2025.day2

import advent.of.code.ContentReader.readFileAsText

fun main() {
    val products = readFileAsText(2025, 2)
        .split(",")
        .map { it.trim().split("-") }
        .map { Product(it[0].toLong(), it[1].toLong()) }


    val incorrectIds = products
        .flatMap { it.getIncorrectIds() }

    println("Incorrect product IDs: ${incorrectIds.sum()}")

}

private data class Product(
    val startRange: Long,
    val endRange: Long,
) {
    fun getIncorrectIds(): List<Long> {
        return (startRange..endRange)
            .map { it.toString() }
            .filter { it.length % 2 == 0 }
            .filter { isTextSameHalf(it) }
            .map { it.toLong() }
    }

    private fun isTextSameHalf(text: String): Boolean {
        val halfLength = text.length / 2
        val firstHalf = text.substring(0, halfLength)
        val secondHalf = text.substring(halfLength)
        return firstHalf == secondHalf
    }
}