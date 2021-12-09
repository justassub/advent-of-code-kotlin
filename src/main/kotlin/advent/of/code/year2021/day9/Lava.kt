package advent.of.code.year2021.day9

class Lava(heightMap: Collection<String>) {

    private val allPoints = heightMap
        .map { numbers -> numbers.toCharArray().map { it.digitToInt() } }
        .withIndex()
        .flatMap { (y, numbers) ->
            numbers.withIndex().map { (x, value) -> SmokeLocation(x = x, y = y, value = value) }
        }

    private val pointWithNeighbours = allPoints.map { Pair(it, findNeighbours(it)) }

    private fun findNeighbours(smokeLocation: SmokeLocation): List<SmokeLocation> {
        return listOfNotNull(
            findPointByXAndYOrNull(smokeLocation.x.inc(), smokeLocation.y),
            findPointByXAndYOrNull(smokeLocation.x.dec(), smokeLocation.y),
            findPointByXAndYOrNull(smokeLocation.x, smokeLocation.y.inc()),
            findPointByXAndYOrNull(smokeLocation.x, smokeLocation.y.dec())
        )
    }

    private fun findPointByXAndYOrNull(x: Int, y: Int): SmokeLocation? {
        return allPoints.firstOrNull { it.x == x && it.y == y }
    }

    fun listAllLowPoints(): List<SmokeLocation> {
        return pointWithNeighbours
            .filter { (p, adj) ->
                adj.all { it.value > p.value }
            }.map { it.first }
    }

    fun listAllBasins(): Set<Set<SmokeLocation>> {
        val basins: MutableSet<Set<SmokeLocation>> = mutableSetOf();
        for (point in allPoints) {
            if (basins.any { it.contains(point) }) {
                continue
            } else {
                basins.add(getBasin(point))
            }
        }
        return basins
    }

    private fun getBasin(startPoint: SmokeLocation): Set<SmokeLocation> {
        if (startPoint.value == 9) {
            return emptySet()
        }
        val basinPoints = mutableSetOf(startPoint);
        val checkedPoints = mutableSetOf(startPoint);
        val uncheckedPoints = findNeighbours(startPoint).toMutableList()

        while (uncheckedPoints.isNotEmpty()) {
            val next = uncheckedPoints.removeFirst();
            checkedPoints.add(next)
            if (next.value != 9) {
                basinPoints.add(next);
                uncheckedPoints.addAll(findNeighbours(next).filterNot { checkedPoints.contains(it) })
            }
        }

        return basinPoints;
    }
}
