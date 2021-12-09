package advent.of.code.year2021.day9

data class SmokeLocation(val x: Int, val y: Int, val value: Int)

fun SmokeLocation.createNeighbours(): Set<SmokeLocation> {
    return setOf(
        this.copy(x = this.x.inc()),
        this.copy(x = this.x.dec()),
        this.copy(y = this.y.inc()),
        this.copy(y = this.y.dec())
    )
}
