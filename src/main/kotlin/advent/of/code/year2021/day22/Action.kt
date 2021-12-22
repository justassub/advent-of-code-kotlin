package advent.of.code.year2021.day22

//on x=-41..4,y=-26..18,z=-26..27
data class Action(
    val fromX: Int,
    val toX: Int,
    val fromY: Int,
    val toY: Int,
    val fromZ: Int,
    val toZ: Int,
    val command: Command
)
