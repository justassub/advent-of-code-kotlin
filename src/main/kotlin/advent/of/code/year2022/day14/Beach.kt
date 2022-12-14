package advent.of.code.year2022.day14

class Beach(
    private val objects: MutableMap<BeachPoint, BeachObject>,
    private val bottom: Boolean = false) {

    private val maxY = objects.maxOf { it.key.y }


    fun playWithSand(stopFunction: (BeachPoint) -> Boolean) {
        while (true){
            var sandPoint = getSandStartingPoint()
            var newPosition = findNextSandPosition(sandPoint)

            do {
                if (stopFunction(newPosition)) {
                    return
                }
                sandPoint = newPosition
                newPosition = findNextSandPosition(sandPoint)
            } while ((newPosition != sandPoint))

            objects[sandPoint] = BeachObject.SAND
        }
    }

    fun getSandStartingPoint() = BeachPoint(500, 0)

    fun isGoingToAbyss(beachPoint: BeachPoint): Boolean {
        return beachPoint.y >= maxY
    }

    private fun findNextSandPosition(beachPoint: BeachPoint): BeachPoint {
        val down = BeachPoint(beachPoint.x, beachPoint.y + 1)
        val downLeft = BeachPoint(beachPoint.x - 1, beachPoint.y + 1)
        val downRight = BeachPoint(beachPoint.x + 1, beachPoint.y + 1)

        return when {
            bottom && beachPoint.y == maxY + 1 -> beachPoint
            down !in objects -> down
            downLeft !in objects -> downLeft
            downRight !in objects -> downRight
            else -> beachPoint
        }
    }

    fun countSand(): Int {
        return objects.count { it.value == BeachObject.SAND }
    }

}
