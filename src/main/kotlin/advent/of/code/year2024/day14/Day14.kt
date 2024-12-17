package advent.of.code.year2024.day14

import advent.of.code.ContentReader

const val X_MAX = 101
const val Y_MAX = 103

const val xHalf = (X_MAX / 2)
const val yHalf = (Y_MAX / 2)

const val SECONDS = 10000


fun main() {
    val robots = ContentReader.readFileAsLines(2024, 14)
        .map(::constructRobotFromLine)
        .toSet()

    repeat(SECONDS) {
        robots.forEach(Robot::move)
        val text = printGraph(robots)
        if(text.contains("#########")) {
            println("Round: $it")
            println(text)
        }

    }
    val quarant1 = robots.count { it.x < xHalf && it.y < yHalf }
    val quarant2 = robots.count { it.x > xHalf && it.y < yHalf }
    val quarant3 = robots.count { it.x < xHalf && it.y > yHalf }
    val quarant4 = robots.count { it.x > xHalf && it.y > yHalf }

    println(quarant1 * quarant2 * quarant3 * quarant4)
}


fun constructRobotFromLine(line: String): Robot {
    //p=0,4 v=3,-3
    return Regex("""p=(.*?),(.*?) v=(.*?),(.*?)""")
        .matchEntire(line)
        ?.destructured
        ?.let { (x, y, vX, vY) -> Robot(x.toInt(), y.toInt(), vX.toInt(), vY.toInt()) }
        ?: throw IllegalArgumentException("Bad Data")
}

data class Robot(var x: Int, var y: Int, val speedX: Int, val speedY: Int) {
    fun move() {
        x += speedX
        y += speedY
        while (!inProperRange()) {
            if (x >= X_MAX) {
                x %= X_MAX
            }
            if (y >= Y_MAX) {
                y %= Y_MAX
            }
            if (x < 0) {
                x += X_MAX
            }
            if (y < 0) {
                y += Y_MAX
            }
        }
    }

    private fun inProperRange(): Boolean {
        return x >= 0 && y >= 0 && x < X_MAX && y < Y_MAX
    }


}

fun printGraph(robots: Set<Robot>): String {
    val points = robots.map { it.x to it.y }.toSet()
    val stringBuilder = StringBuilder()
    for (y in 0..<Y_MAX) {
        for (x in 0..<X_MAX) {
            if (points.contains(x to y)) {
                stringBuilder.append("#")
            } else {
                stringBuilder.append(".")
            }
        }
        stringBuilder.append("\n")
    }
    return stringBuilder.toString()

}