package advent.of.code.year2022.day11

import kotlin.system.measureTimeMillis


const val day = 11


fun main() {
    val timeInMillis = measureTimeMillis {
        solve1()
        solve2()
    }

    println("Task 11 finished in $timeInMillis ms")
}

fun solveTestCase() {
    val monkeys = MonkeyGenerator.generateTest()
    val chineseRemainder = 23 * 19 * 13 * 17

    val result = solve(monkeys, 10_000) { it % chineseRemainder }
    println(result)
}

fun solve1() {
    val monkeys = MonkeyGenerator.generateDefaultMonkeys()
    val result1 = solve(monkeys, 20) { it / 3 }
    println(result1)
}

fun solve2() {
    val monkeys = MonkeyGenerator.generateDefaultMonkeys()
    val chineseRemainder = 3 * 13 * 2 * 11 * 19 * 17 * 5 * 7
    val result2 = solve(monkeys, 10_000) { it % chineseRemainder }
    println(result2)
}

fun solve(monkeys: Map<Int, Monkey>, rounds: Int = 20, newItemLevelChanger: ((Long) -> Long)): Long {
    repeat(rounds) {
        monkeys.forEach { (_, monkey) ->
            monkey.inpectAll(newItemLevelChanger)
                .forEach {
                    monkeys.getValue(it.key).addItems(it.value)
                }
        }
    }
    return monkeys.map { it.value.getInspectedItemCount() }
        .sortedDescending()
        .take(2)
        .reduce { a, b -> a * b }

}


