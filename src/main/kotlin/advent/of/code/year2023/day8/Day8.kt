package advent.of.code.year2023.day8

import advent.of.code.ContentReader
import advent.of.code.util.Builder
import advent.of.code.util.number.GCDCalculator

fun main() {
    val map = ContentReader.readFileAsLines(2023, 8)
        .let { MapConstructor.createInitialMap(it) }

    val steps = Builder.leftOrRightBuilderSingleLine(
        "LRRLRLRRRLLRLRRRLRLLRLRLRRLRLRRLRRLRLRLLRRRLRRLLRRRLRRLRRRLRRLRLRLLRRLRLRRLLRRRLLLRRRLLLRRLRLRRLRLLRRRLRRLRRRLRRLLRRRLRRRLRRRLRLRRLRLRRRLRRRLRRLRLRRLLRRRLRRLLRRLRRLRLRLRRRLRLLRRRLRRLRRRLLRRLLLLLRRRLRRLLLRRRLRRRLRRLRLLLLLRLRRRLRRRLRLRRLLLLRLRRRLLRRRLRRRLRLRLRRLRRLRRLRLRLLLRLRRLRRLRRRLRRRLLRRRR",
        'L', 'R'
    )

    val mapFollower = MapFollower(map, steps)

    println(mapFollower.calculateMinSteps("AAA") { it == "ZZZ" })


    map.keys.filter { it.endsWith("A") }
        .map { element -> mapFollower.calculateMinSteps(element) { it.endsWith("Z") } }
        .run { println(GCDCalculator.lcm(this)) }
}