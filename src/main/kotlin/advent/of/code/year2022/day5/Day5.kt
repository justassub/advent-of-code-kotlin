package advent.of.code.year2022.day5

import advent.of.code.year2022.ContentReader2022
import kotlin.system.measureTimeMillis

const val day = 5

val text = """
    [G]         [P]         [M]    
    [V]     [M] [W] [S]     [Q]    
    [N]     [N] [G] [H]     [T] [F]
    [J]     [W] [V] [Q] [W] [F] [P]
[C] [H]     [T] [T] [G] [B] [Z] [B]
[S] [W] [S] [L] [F] [B] [P] [C] [H]
[G] [M] [Q] [S] [Z] [T] [J] [D] [S]
[B] [T] [M] [B] [J] [C] [T] [G] [N]
    """.trimIndent()
    .lines()

fun main() {
    val timeInMillis = measureTimeMillis { solve() }

    println("Task 5 finished in $timeInMillis ms")
}

fun solve() {
    part1()
    part2()
}


fun part1() {
    val initiate = OriginalCrateStackGenerator.generateOriginalStacks(text)
    val commands = ContentReader2022.readFileAsLines(day)
        .map { CrateCommandGenerator.createCommand(it) }


    CrateCommandRunner(initiate, commands).run(false)
    val result = initiate.entries.sortedBy { it.key }.map { it.value.getCrates().last() }.joinToString("")
    println(result)
}
fun part2() {

    val initiate = OriginalCrateStackGenerator.generateOriginalStacks(text)
    val commands = ContentReader2022.readFileAsLines(day)
        .map { CrateCommandGenerator.createCommand(it) }


    CrateCommandRunner(initiate, commands).run(true)
    val result = initiate.entries.sortedBy { it.key }.map { it.value.getCrates().last() }.joinToString("")
    println(result)
}
