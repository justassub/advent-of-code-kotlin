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

    findAllIgnoredCommands(text)
        .foldRight(text) { line, seed -> seed.replace(line, "") }
        .let { findAllValidMultCommands(it) }
        .sumOf { it.first * it.second }
        .printResult()

}

fun findAllIgnoredCommands(text: String): List<String> {
    val ignoredCommands = mutableListOf<String>()
    var nextStop = text.indexOf(DONT)
    while (true) {
        val nextStart = text.indexOf(DO, nextStop)
        if (nextStart == -1) {
            ignoredCommands.add(text.substring(nextStop))
            break
        }
        ignoredCommands.add(text.substring(nextStop, nextStart))
        nextStop = text.indexOf(DONT, nextStart)
    }
    return ignoredCommands
}

fun findAllValidMultCommands(text: String): List<Pair<Long, Long>> {
    val regex = Regex("mul\\((\\d+),(\\d+)\\)")
    return regex.findAll(text)
        .map { (it.groupValues[1].toLong() to it.groupValues[2].toLong()) }
        .toList()
}
