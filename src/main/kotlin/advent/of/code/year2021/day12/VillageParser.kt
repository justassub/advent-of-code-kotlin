package advent.of.code.year2021.day12

object VillageParser {
    private val regex = Regex("""(.*?)-(.*?)""")

    fun parseVillages(lines: List<String>): List<Village> {
        val allConnections = lines
            .map { cityShowsTo(it) }

        val allCities = allConnections
            .flatMap { setOf(it.first, it.second) }
            .toSet()
            .associateWith { Village(name = it, villageType = getVillageType(it)) }

        allCities.keys
            .forEach { key ->
                allConnections
                    .filter { it.first == key || it.second == key }
                    .flatMap { it.toList() }
                    .filter { it != key }
                    .forEach {
                        allCities.getValue(key)
                            .addAdjVillage(allCities.getValue(it))
                    }
            }

        return allCities.values.toList()

    }

    private fun cityShowsTo(line: String): Pair<String, String> {
        return regex.matchEntire(line)
            ?.destructured
            ?.let { (cityA, cityB) -> Pair(cityA, cityB) }
            ?: throw IllegalArgumentException("Line $line was incorrect")
    }

    private fun getVillageType(villageName: String): VillageType {
       return when (villageName) {
            "start" -> VillageType.START
            "end" -> VillageType.FINISH
            villageName.uppercase() -> VillageType.LARGE
            else -> VillageType.SMALL
        }
    }
}
