package advent.of.code.year2022.day18

import advent.of.code.util.Point3D
import advent.of.code.util.build3DPointFromLine
import advent.of.code.util.findNeighbours
import advent.of.code.year2022.ContentReader2022
import kotlin.system.measureTimeMillis


const val day = 18;


fun main() {
    val timeInMillis = measureTimeMillis { solve() }

    println("Task 18 finished in $timeInMillis ms")
}

fun solve() {
    val points = ContentReader2022.readFileAsLines(day)
        .map { build3DPointFromLine(it, ",") }
        .toSet()

    println(solvePart1(points))
}

fun solvePart1(points: Set<Point3D>): Int {
    return points
        .flatMap { it.findNeighbours() }
        .count { it !in points }
}