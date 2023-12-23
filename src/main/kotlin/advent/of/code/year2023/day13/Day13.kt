package advent.of.code.year2023.day13

import advent.of.code.ContentReader
import advent.of.code.util.PointBuilder
import advent.of.code.util.printResult


fun main() {
    ContentReader.readAsGroupOfLinesSplitByEmptyLine(2023, 13)
        .map { PointBuilder.createPointsWithValuesFromLines(it) }
        .map { MirrorFinder(it).findMirrorLines() }
        .groupBy({ it.first }) { it.second + 1 }
        .map { it.key to it.value.sum() }.sumOf {
            when (it.first) {
                MirrorPosition.VERTICAL -> it.second
                MirrorPosition.HORIZONTAL -> it.second * 100
            }
        }
        .printResult()



}