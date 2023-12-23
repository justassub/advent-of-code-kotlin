package advent.of.code.year2023.day13

import advent.of.code.util.Point
import advent.of.code.util.findMinMaxValues

enum class MirrorPosition {
    VERTICAL, HORIZONTAL
}

class MirrorFinder(val data: Map<Point, Char>) {
    private val minMaxPoints = data.findMinMaxValues()

    fun findMirrorLines(): Pair<MirrorPosition, Int> {
        val possibleMirrors = findVerticalMirrors() + findHorizontalMirrors()

        if (possibleMirrors.size != 1) {
            throw IllegalArgumentException("Not found or found too many mirrors")
        }

        return possibleMirrors.first()
    }

    private fun findVerticalMirrors(): List<Pair<MirrorPosition, Int>> {
        val lines = (minMaxPoints.minX..minMaxPoints.maxX)
            .map { n ->
                data.filter { it.key.x == n }.map { it.key.y to it.value }.sortedBy { it.first }
                    .joinToString("") { it.second + "" }

            }
            .mapIndexed { index, s -> index to s }
            .toMap()

        return findMirrors(lines, MirrorPosition.VERTICAL)
    }

    private fun findHorizontalMirrors(): List<Pair<MirrorPosition, Int>> {
        val lines = (minMaxPoints.minY..minMaxPoints.maxY)
            .map { n ->
                data.filter { it.key.y == n }.map { it.key.x to it.value }.sortedBy { it.first }
                    .joinToString("") { it.second + "" }

            }
            .mapIndexed { index, s -> index to s }
            .toMap()
        return findMirrors(lines, MirrorPosition.HORIZONTAL)
    }


    private fun findMirrors(lines: Map<Int, String>, position: MirrorPosition): List<Pair<MirrorPosition, Int>> {
        return lines.entries
            .asSequence()
            .sortedBy { it.key }
            .zipWithNext()
            .filter { it.first.value == it.second.value }
            .filter { isMirror(it.first.toPair(), lines) }
            .map { position to it.first.key }
            .toList()
    }

    private fun isMirror(lineAndIndex: Pair<Int, String>, lines: Map<Int, String>): Boolean {
        val realIndex = lineAndIndex.first
        val compareAmount = if (lines.size / 2 > realIndex) {
            realIndex
        } else {
            lines.size - (realIndex + 2)
        }

        return (0 until compareAmount)
            .all {
                lines[lineAndIndex.first - 1 - it] == lines[lineAndIndex.first + 2 + it]
            }

    }

}
