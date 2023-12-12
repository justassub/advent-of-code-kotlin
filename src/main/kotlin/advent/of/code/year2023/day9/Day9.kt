package advent.of.code.year2023.day9

import advent.of.code.ContentReader

fun main() {
    val numbers = ContentReader.readFileAsLines(2023, 9)
        .map { it.split(" ").map { n -> n.toInt() } }
        .map { CustomNumbersGenerator(it) }


    numbers
        .sumOf { it.generateNext() }
        .run { println(this) }

    numbers
        .sumOf { it.generateFirst() }
        .run { println(this) }
}