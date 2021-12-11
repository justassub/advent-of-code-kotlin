package advent.of.code.year2021.day11

class Stage(val octupusById: Map<Long, Octupus>) {
    val flashedOctupus: MutableSet<Long> = mutableSetOf()

    fun start() {
        octupusById.values
            .forEach { it.increaseEnergyLevel() }
        processFlashing()
        resetAllFlashed()
    }

    private fun processFlashing() {
        val notFlashed = findNotFlashedOctupuses()
        if (notFlashed.isEmpty()) {
            return
        } else {
            notFlashed.forEach { flashOctupus(it) }
            processFlashing()
        }
    }

    private fun flashOctupus(octupus: Octupus) {

        octupus.neighbours
            .forEach { octupusById.getValue(it).increaseEnergyLevel() }

        flashedOctupus.add(octupus.id)
    }

    private fun findNotFlashedOctupuses(): List<Octupus> {
        return octupusById
            .filter { it.value.energyLevel > 9 }
            .filter { !flashedOctupus.contains(it.key) }
            .map { it.value }
    }

    private fun resetAllFlashed() {
        return octupusById.values
            .filter { it.energyLevel > 9 }
            .forEach { it.flash() }

    }

}
