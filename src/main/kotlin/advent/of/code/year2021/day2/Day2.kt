package advent.of.code.year2021.day2

import advent.of.code.year2021.ContentReader

class Day2 {
    companion object CommandParser {
        fun parseCommands(lines: List<String>): List<Pair<SubmarineCommand, Int>> {
            return lines.map { it.split(" ") }
                .map { Pair(SubmarineCommand.valueOf(it.first().uppercase()), it.last().toInt()) }
        }
    }

    fun part1(commands: List<Pair<SubmarineCommand, Int>>): Int {
        val submarine = Submarine()
        commands.forEach { moveSubmarine(submarine, it) }
        return submarine.debt * submarine.horizontalPosition
    }

    fun part2(commands: List<Pair<SubmarineCommand, Int>>): Int {
        val submarine = SubmarineWithAim()
        commands.forEach { moveSubmarine(submarine, it) }
        return submarine.debt * submarine.horizontalPosition
    }

    private fun moveSubmarine(submarine: Submarine, submarineCommand: Pair<SubmarineCommand, Int>) {
        when (submarineCommand.first) {
            SubmarineCommand.FORWARD -> submarine.moveForward(submarineCommand.second)
            SubmarineCommand.DOWN -> submarine.moveDown(submarineCommand.second)
            SubmarineCommand.UP -> submarine.moveUp(submarineCommand.second)
        }
    }
}

fun main() {
    val day2 = Day2()
    val commands = ContentReader.readFileAsLines(2)
    val submarineCommands = Day2.parseCommands(commands)
    println(day2.part1(submarineCommands))
    println(day2.part2(submarineCommands))
}