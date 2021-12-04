package advent.of.code.year2021.day4

import advent.of.code.year2021.ContentReader

class Day4 {

    fun task1(groupsWithNumbers: List<String>, guessingNumbers: List<Int>): Long {
        val boards = buildBoards(groupsWithNumbers)
        for (guessingNumber in guessingNumbers) {
            val winners = boards
                .map { it.playNumberAndCheckIfWinners(guessingNumber) }
                .filter { it.first }
                .map { it.second }
            if (winners.isNotEmpty()) {
                return 1L * guessingNumber * winners.first().sumUnmarkedNumbers()
            }
        }
        throw IllegalAccessException("Should not reach this point")
    }


    fun task2(groupsWithNumbers: List<String>, guessingNumbers: List<Int>): Long {
        val boards = buildBoards(groupsWithNumbers)
            .toMutableList()
        for (guessingNumber in guessingNumbers) {
            val winners = boards
                .map { it.playNumberAndCheckIfWinners(guessingNumber) }
                .filter { it.first }
                .map { it.second }
            if (winners.isNotEmpty() && boards.size > 1) {
                boards.removeAll(winners)
                continue
            }
            if (winners.isNotEmpty()) {
                return 1L * guessingNumber * winners.first().sumUnmarkedNumbers()
            }
        }
        throw IllegalAccessException("Should not reach this point")

    }

    private fun buildBoards(groupsWithNumbers: List<String>): List<Board> {
        val groups = groupsWithNumbers
            .filter { it.isNotEmpty() }
            .chunked(5)

        val boards = groups.map {
            BoardBuilder.buildBoardFromLines(it)
        }
        return boards
    }
}

fun main() {
    val day4 = Day4()
    val data = ContentReader.readFileAsLines(4)
    val playingNumbers =
        "15,62,2,39,49,25,65,28,84,59,75,24,20,76,60,55,17,7,93,69,32,23,44,81,8,67,41,56,43,89,95,97,61,77,64,37,29,10,79,26,51,48,5,86,71,58,78,90,57,82,45,70,11,14,13,50,68,94,99,22,47,12,1,74,18,46,4,6,88,54,83,96,63,66,35,27,36,72,42,98,0,52,40,91,33,21,34,85,3,38,31,92,9,87,19,73,30,16,53,80"
            .split(",")
            .map { it.toInt() }

    val part1 = day4.task1(data, playingNumbers)
    val part2 = day4.task2(data, playingNumbers)
    println(part1)
    println(part2)
}