package advent.of.code.year2022.day19

class BluePrintCalculator() {

    fun calculateMaxGeode(min: Int, storage: Storage): Int {
        if (min == 0) {
            return storage.materialStorage.geodes
        }
        val collected = storage.collect()
        return if (collected.canCraftGeodes()) {

            // IT SHOULD BE ALWAYS BEST TO CREATE GEODES
            calculateMaxGeode(min - 1, collected.craftGeodeMachine(min))
        } else {
            setOf(
                collected,
                collected.craftOreMachine(),
                collected.craftClayMachine(),
                collected.craftObsidianMachine()
            )
                .maxOf { calculateMaxGeode(min - 1, it) }
        }
    }
}
