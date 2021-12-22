package advent.of.code.year2021.day21

data class Player(
    val startingPosition: Int
) {
    var score = 0
    private var position = startingPosition

    fun move(moveBy: Int) {
        val updatedPosition = this.position + moveBy % 10
        if (updatedPosition > 10) {
            this.position = updatedPosition % 10
        } else {
            this.position = updatedPosition
        }
        score += position
    }

    fun alreadyWon() = score >= 1000

}
