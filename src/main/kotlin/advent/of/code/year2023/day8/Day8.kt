package advent.of.code.year2023.day8

import advent.of.code.ContentReader
import advent.of.code.util.Builder

fun main() {
    val map = ContentReader.readFileAsLines(2023, 8)
        .let { MapConstructor.createInitialMap(it) }

    val steps = Builder.leftOrRightBuilderSingleLine(
        "LRRLRLRRRLLRLRRRLRLLRLRLRRLRLRRLRRLRLRLLRRRLRRLLRRRLRRLRRRLRRLRLRLLRRLRLRRLLRRRLLLRRRLLLRRLRLRRLRLLRRRLRRLRRRLRRLLRRRLRRRLRRRLRLRRLRLRRRLRRRLRRLRLRRLLRRRLRRLLRRLRRLRLRLRRRLRLLRRRLRRLRRRLLRRLLLLLRRRLRRLLLRRRLRRRLRRLRLLLLLRLRRRLRRRLRLRRLLLLRLRRRLLRRRLRRRLRLRLRRLRRLRRLRLRLLLRLRRLRRLRRRLRRRLLRRRR",
        'L', 'R'
    )

    println(MapFollower(map,steps).calculateMinSteps("ZZZ"))
}