package advent.of.code.year2022.day3

data class Rucksack(val items: List<String>) {
    fun findCommonElement(): Set<Char> {
        return if (items.size < 2) {
            emptySet()
        } else {
            items
                .first()
                .filter { items.all { item -> item.contains(it) } }
                .toSet()
        }
    }
}
