package advent.of.code.year2021.day11

class Octupus(val id: Long, initialEnergyLevel: Int, val neighbours: Set<Long>) {
    var energyLevel = initialEnergyLevel;

    fun increaseEnergyLevel() {
        energyLevel += 1
    }

    fun flash() {
        energyLevel = 0
    }
}
