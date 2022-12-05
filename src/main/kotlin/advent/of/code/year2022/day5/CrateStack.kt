package advent.of.code.year2022.day5


typealias Crate = Char

class CrateStack {
    private val crates: ArrayDeque<Crate> = ArrayDeque()

    fun retrieveLastNAndReverse(numberOfCrates: Int): List<Crate> {
        return (1..numberOfCrates)
            .mapNotNull { crates.removeLastOrNull() }

    }

    fun addSingleCrateOnTop(crate: Crate) {
        crates.addLast(crate)
    }

    fun addNumberOfCratesOnTop(newCrates: Collection<Crate>) {
        crates.addAll(newCrates)
    }

    fun getCrates() = crates
}
