package advent.of.code.year2023.day2

object GameValidator {

    fun isGameValid(game: Game, rules: Map<COLOUR, Int>): Boolean {
        return game.sets.all {
            it.subset.none { gameSubset ->
                rules.getOrDefault(gameSubset.colour, 0) < gameSubset.amount
            }
        }
    }
}