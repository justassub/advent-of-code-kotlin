package advent.of.code.year2021.day13

import advent.of.code.year2021.ContentReader
import advent.of.code.year2021.Point
import advent.of.code.year2021.PointOrMark

class Day13 {
    companion object PointBuilder {
        fun buildPoint(line: String): Point {
            val coordinates = line.split(",")
                .map { it.toInt() }

            return Point(coordinates.first(), coordinates.last())
        }

        fun buildPointsAndMarks(marks: Set<Point>): Set<PointOrMark> {
            val maxX = marks.maxOf { it.x }
            val maxY = marks.maxOf { it.y }

            fun buildMarkOrPoint(p: Point): PointOrMark {
                return if (marks.contains(p)) {
                    PointOrMark(p.x, p.y, true)
                } else {
                    PointOrMark(p.x, p.y, false)
                }
            }

            return (0..maxX)
                .flatMap { x -> (0..maxY).map { buildMarkOrPoint(Point(x, it)) } }
                .toSet()
        }
    }

    fun task1(lines: List<String>, commands: List<String>): Int {
        val marks = lines.map { buildPoint(it) }.toSet()
        val pointsAndMarks = buildPointsAndMarks(marks)
        val foldCommands = commands.map { parseFoldCommand(it) }


        return foldCommands
            .fold(pointsAndMarks) { res, command -> fold(res, command) }
            .count { it.isMark }
    }

    fun task2(lines: List<String>, commands: List<String>) {
        val marks = lines.map { buildPoint(it) }.toSet()
        val pointsAndMarks = buildPointsAndMarks(marks)


        commands
            .asSequence()
            .map { parseFoldCommand(it) }
            .fold(pointsAndMarks) { res, command -> fold(res, command) }
            .sortedWith(compareBy({ it.y }, { it.x }))
            .chunked(40)
            .map { line -> line.joinToString("") { it.extractSymbol() } }
            .forEach { println(it) }
    }


    private fun parseFoldCommand(command: String): FoldCommand {
        return Regex("""fold along (.*?)=(.*?)""")
            .matchEntire(command)
            ?.destructured
            ?.let { (letter, digit) -> FoldCommand(CoordinateType.valueOf(letter.uppercase()), digit.toInt()) }
            ?: throw IllegalArgumentException("Bad Data")
    }

    private fun fold(points: Set<PointOrMark>, command: FoldCommand): Set<PointOrMark> {
        return when (command.coordinateType) {
            CoordinateType.X -> foldViaX(points, command.number)
            CoordinateType.Y -> foldViaY(points, command.number)
        }
    }

    private fun foldViaY(points: Set<PointOrMark>, number: Int): Set<PointOrMark> {
        val notChangedLines = points.filter { it.y < number }
            .toSet()
        val changedLines = points
            .filter { it.y > number }
            .map { PointOrMark(x = it.x, y = number - (it.y - number), isMark = it.isMark) }
            .toSet()
        return mergeFoldedData(notChangedLines, changedLines)
    }

    private fun foldViaX(points: Set<PointOrMark>, number: Int): Set<PointOrMark> {
        val notChangedLines = points.filter { it.x < number }
            .toSet()
        val changedLines = points
            .filter { it.x > number }
            .map { PointOrMark(x = number - (it.x - number), y = it.y, isMark = it.isMark) }
            .toSet()
        return mergeFoldedData(notChangedLines, changedLines)
    }

    private fun mergeFoldedData(set1: Set<PointOrMark>, set2: Set<PointOrMark>): Set<PointOrMark> {
        val merge = (set1 + set2).toSet()
        val marks = merge
            .filter { it.isMark }
        val includePoints = merge
            .filterNot { it.isMark }
            .filterNot { marks.contains(PointOrMark(it.x, it.y, true)) }

        return (marks + includePoints).toSet()

    }
}

fun main() {
    val data = ContentReader.readFileAsLines(13)
    val emptyLineIndex = data.indexOf("")
    val lines = data.take(emptyLineIndex)
    val commands = data.drop(emptyLineIndex + 1).dropLast(1)

    val day13 = Day13()
    println(day13.task1(lines, commands.subList(0, 1)))
    day13.task2(lines, commands)
}
