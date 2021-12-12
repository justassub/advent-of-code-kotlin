package advent.of.code.year2021.day12

class Village(val name: String, val villageType: VillageType) {
    val adjVillages = mutableSetOf<Village>()

    fun addAdjVillage(village: Village) {
        adjVillages.add(village)
    }
}
