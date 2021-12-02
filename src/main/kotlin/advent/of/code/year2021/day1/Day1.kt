package advent.of.code.year2021.day1

import advent.of.code.year2021.ContentReader

class Day1 {
    /**
     * You need to count number how many times element increases
     */
    fun part1(data: List<Int>): Int {
        return data.zipWithNext().count { it.first < it.second }
    }

    fun part2(data: List<Int>): Int {
        val threeNeighboursSum = data.windowed(size = 3, transform = { it.sum() })
        return part1(threeNeighboursSum)
    }

}

fun main() {
    val day = 1
    val d1 = Day1()
    val data = ContentReader.readFileAsNumbers(day)
    val increases = d1.part1(data)
    val increasesOfThreeNeighbours = d1.part2(data)
    println(increases)
    println(increasesOfThreeNeighbours)
}