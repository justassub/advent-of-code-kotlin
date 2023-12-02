package advent.of.code.year2023.day2

object RuleFinder {

    fun findMinimumRulesForEachGame(game: Game): Map<COLOUR, Int> {

        return game.sets
            .flatMap { s -> s.subset.map { it.colour to it.amount } }
            .groupBy({ it.first }, { it.second })
            .mapValues { it.value.max() }
    }
}