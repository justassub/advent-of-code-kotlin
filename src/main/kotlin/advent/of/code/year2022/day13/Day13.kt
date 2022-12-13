package advent.of.code.year2022.day13

import advent.of.code.year2022.ContentReader2022

import kotlin.system.measureTimeMillis

const val day = 13
fun main() {

    val timeInMillis = measureTimeMillis { solve() }

    println("Task 13 finished in $timeInMillis ms")
}

fun solve() {
    val packets = ContentReader2022.readFileAsLines(day).windowed(2, 3)
        .map { group -> group.map { PacketGenerator.parseList(it) } }
        .mapIndexed { index, list -> Triple(index, list.first(), list.last()) }

    println(solve1(packets))
    println(solve2(packets))
}

fun solve1(packets: List<Triple<Int, List<Any>, List<Any>>>): Int {
    return packets
        .filter { PacketComparator.compare(it.second, it.third) }
        .map { it.first + 1 }
        .sumOf { it }
}

fun solve2(packets: List<Triple<Int, List<Any>, List<Any>>>): Int {
    val add1 = listOf(listOf(2))
    val add2 = listOf(listOf(6))
    val sortedNewPackets = (packets
        .flatMap { listOf(it.second, it.third) } + add1 + add2)
        .sortedWith(PacketSortAlgo())

    return (sortedNewPackets.indexOf(listOf(2)) + 1) * (sortedNewPackets.indexOf(listOf(6)) + 1)
}





