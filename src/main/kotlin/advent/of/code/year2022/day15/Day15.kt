package advent.of.code.year2022.day15

import advent.of.code.year2022.ContentReader2022
import kotlin.system.measureTimeMillis

const val day = 15


fun main() {

    val timeInMillis = measureTimeMillis { solve() }

    println("Task 14 finished in $timeInMillis ms")
}

fun solve() {
    val sensors = ContentReader2022.readFileAsLines(day)
        .map { SignalObjectGenerator.buildSignal(it) }
    //println(solvePart1(sensors, 10))
    println(solvePart2(sensors, Range15(0, 20, 0, 20)))
}

fun solvePart1(sensors: List<Sensor>, y: Int = 10): Int {
    val occupiedSlots = sensors.flatMap { listOf(NotBeacon(it.x, it.y), (NotBeacon(it.beacon.x, it.beacon.y))) }.toSet()
    return sensors.flatMap { it.findAllExistingPointsInY(y) }.distinct()
        .count { it !in occupiedSlots }
}

fun solvePart2(sensors: List<Sensor>, range15: Range15 = Range15(0, 20, 0, 20)): Long {

    return sensors
        .foldRight(range15) { s, r -> s.reducePossibleDisruptorSensor(r) }
        .let { findPossibleSolutions(sensors, it) }

}

fun findPossibleSolutions(sensors: List<Sensor>, range15: Range15): Long {
    for (x in (range15.minX..4_000_000)) {
        for (y in (range15.maxY..4_000_000)) {
            if (sensors.none { it.canCatch(x, y) }) {
                return x * 4_000_000L + y
            }
        }
    }
    return 0
}
