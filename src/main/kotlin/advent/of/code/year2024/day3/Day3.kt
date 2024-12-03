package advent.of.code.year2024.day3

import advent.of.code.ContentReader
import advent.of.code.util.printResult

val DO = "do()"
val DONT = "don't()"

fun main() {
    val text = ContentReader.readFileAsText(2024, 3)

  findAllValidMultCommands(text)
        .sumOf { it.first * it.second }
        .printResult()


findAllValidCommands(text)
        .sumOf { it.first * it.second }
        .printResult()

}

fun findAllValidCommands(
    text: String
): List<Pair<Long, Long>> {
    var textToValidate = text

    val validableText = mutableListOf<String>()

    while (textToValidate.isNotBlank()) {
        val nextDont = textToValidate.indexOf(DONT)
        if (nextDont == -1) {
            validableText.add(textToValidate)
            break
        }

        val validText = textToValidate.substring(0, nextDont)

        validableText.add(validText)
        textToValidate = textToValidate.substring(validText.length)

        val nextDo = textToValidate.indexOf(DO)
        if (nextDo == -1) {
            break
        }
        val notValidText = textToValidate.substring(0, nextDo + DO.length)
        textToValidate = textToValidate.substring(notValidText.length)
    }

    return validableText.flatMap { findAllValidMultCommands(it) }
}


fun findAllValidMultCommands(text: String): List<Pair<Long, Long>> {
    val regex = Regex("mul\\((\\d+),(\\d+)\\)")
    return regex.findAll(text)
        .map { (it.groupValues[1].toLong() to it.groupValues[2].toLong()) }
        .toList()
}
