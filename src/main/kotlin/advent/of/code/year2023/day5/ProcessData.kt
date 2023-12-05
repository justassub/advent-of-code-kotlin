package advent.of.code.year2023.day5

data class ProcessData(
    val seedFrom: SeedLevel,
    val seedTo: SeedLevel,
    val processStartInc: Long,
    val processEndInc: Long,
    val changeBy: Long
)