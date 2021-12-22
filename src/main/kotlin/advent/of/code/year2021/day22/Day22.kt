package advent.of.code.year2021.day22

import advent.of.code.year2021.ContentReader

class Day22 {
    fun task1(lines: List<String>): Int {
        val commands = lines
            .map { ActionParser.parseAction(it) }
            .filter { it.fromX >= -50 && it.toX <= 50 }
            .filter { it.fromY >= -50 && it.toY <= 50 }
            .filter { it.fromZ >= -50 && it.toZ <= 50 }

        return commands
            .fold(emptySet<Cube>()) { cubes, action -> doAction(cubes, action) }
            .size
    }

    fun doAction(turnedOnCubes: Set<Cube>, action: Action): Set<Cube> {
        return when (action.command) {
            Command.ON -> turnOnCubes(turnedOnCubes, action)
            Command.OFF -> turnOffCubes(turnedOnCubes, action)
        }
    }

    private fun turnOnCubes(turnedOnCubes: Set<Cube>, action: Action): Set<Cube> {
        return (action.fromX..action.toX)
            .flatMap { x ->
                (action.fromY..action.toY).flatMap { y ->
                    (action.fromZ..action.toZ)
                        .map { z -> Cube(x, y, z) }
                }
            }
            .toSet()
            .plus(turnedOnCubes)
    }

    private fun turnOffCubes(turnedOnCubes: Set<Cube>, action: Action): Set<Cube> {
        return turnedOnCubes
            .filterNot { shouldCubeBeTurnedOff(it, action) }
            .toSet()
    }

    private fun shouldCubeBeTurnedOff(
        cube: Cube,
        action: Action
    ): Boolean {
        return cube.x >= action.fromX && cube.x <= action.toX
                && cube.y >= action.fromY && cube.y <= action.toY
                && cube.z >= action.fromZ && cube.z <= action.toZ
    }

}


object ActionParser {
    private val regex = Regex("""(.*?) x=(.*?)[.][.](.*?),y=(.*?)[.][.](.*?),z=(.*?)[.][.](.*?)""")

    fun parseAction(line: String): Action {
        return regex.matchEntire(line)
            ?.destructured
            ?.let { (command, minX, maxX, minY, maxY, minZ, maxZ) ->
                Action(
                    minX.toInt(),
                    maxX.toInt(),
                    minY.toInt(),
                    maxY.toInt(),
                    minZ.toInt(),
                    maxZ.toInt(),
                    Command.valueOf(command.uppercase())
                )
            } ?: throw IllegalArgumentException("Bad Line to parse $line")
    }
}

fun main() {
    val lines = ContentReader.readFileAsLines(22)
    val day22 = Day22()
    println(day22.task1(lines))
}
