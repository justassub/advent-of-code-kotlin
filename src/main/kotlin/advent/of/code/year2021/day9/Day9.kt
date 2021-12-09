package advent.of.code.year2021.day9

import advent.of.code.year2021.ContentReader

class Day9 {
    fun part1(data: List<String>): Int {
        val lava = Lava(data)
        return lava.listAllLowPoints().sumOf { it.value + 1 }
    }

    fun part2(data: List<String>): Int {
        val lava = Lava(data)
        return lava
            .listAllBasins()
            .sortedBy { it.size }
            .reversed()
            .take(3)
            .map { it.size }
            .reduce(Int::times)
    }
}

fun main() {
    val day = Day9()
    val data = ContentReader.readFileAsLines(9)
    println(day.part1(data))
    println(day.part2(data))
}
