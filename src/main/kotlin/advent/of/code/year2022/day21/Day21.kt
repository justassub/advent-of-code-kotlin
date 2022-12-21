package advent.of.code.year2022.day21

import advent.of.code.year2022.ContentReader2022
import kotlin.system.measureTimeMillis

const val day = 21
fun main() {
    val timeInMillis = measureTimeMillis { solve() }

    println("Task 21 finished in $timeInMillis ms")
}


fun solve() {
    val monkeys = ContentReader2022.readFileAsLines(day)
        .let { MonkeyGenerator.createMonkeys(it) }


    println(solvePart1(monkeys))
    println(solvePart2(monkeys))
}


fun solvePart1(monkeys: Map<String, AnyMonkey>): Long {
    return monkeys.getValue("root").getDigit(monkeys)
}

fun solvePart2(monkeys: Map<String, AnyMonkey>): Long {
    val root = monkeys.getValue("root") as ComplexMonkey
    val me = monkeys.getValue("humn") as SimpleMonkey

    var n = 1L
    me.number = n
    while (!root.bothNumbersAreSame(monkeys)) {
        me.number = ++n
    }
    return n
}
