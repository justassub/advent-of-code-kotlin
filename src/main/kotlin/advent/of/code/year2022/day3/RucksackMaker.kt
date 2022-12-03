package advent.of.code.year2022.day3

object RucksackMaker {

    fun makeRucksackBySplitInHalf(line: String): Rucksack {
        return Rucksack(line.chunked(line.length / 2));
    }
}
