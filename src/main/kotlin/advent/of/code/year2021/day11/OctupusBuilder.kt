package advent.of.code.year2021.day11

object OctupusBuilder {

    fun buildOctupuses(lines: List<String>): Map<Long, Octupus> {
        val xMax = lines.first().length
        val yMax = lines.size
        var nextId = 1L
        val octData = mutableSetOf<OctPrimaryData>()
        for (x in 0 until xMax) {
            for (y in 0 until yMax) {
                octData.add(OctPrimaryData(nextId, x, y, lines[x][y].digitToInt()))
                nextId = nextId.inc()
            }
        }
        return octData
            .map { createOctupFromPrimaryData(it, octData) }
            .associateBy { it.id }
    }

    private fun createOctupFromPrimaryData(octPrimaryData: OctPrimaryData, allOctupus: Set<OctPrimaryData>): Octupus {
        val x = octPrimaryData.x
        val y = octPrimaryData.y
        val adjOct = setOfNotNull(
            findByCoordinatesOrNotNull(x, y.dec(), allOctupus),
            findByCoordinatesOrNotNull(x.inc(), y.dec(), allOctupus),
            findByCoordinatesOrNotNull(x.inc(), y, allOctupus),
            findByCoordinatesOrNotNull(x.inc(), y.inc(), allOctupus),
            findByCoordinatesOrNotNull(x, y.inc(), allOctupus),
            findByCoordinatesOrNotNull(x.dec(), y.inc(), allOctupus),
            findByCoordinatesOrNotNull(x.dec(), y, allOctupus),
            findByCoordinatesOrNotNull(x.dec(), y.dec(), allOctupus)
        )

        return Octupus(
            id = octPrimaryData.id,
            initialEnergyLevel = octPrimaryData.initialEnergyLevel,
            neighbours = adjOct
        )
    }

    private fun findByCoordinatesOrNotNull(x: Int, y: Int, allOctupus: Set<OctPrimaryData>): Long? {
        return allOctupus.firstOrNull { oct -> oct.x == x && oct.y == y }?.id
    }
}

private data class OctPrimaryData(val id: Long, val x: Int, val y: Int, val initialEnergyLevel: Int)
