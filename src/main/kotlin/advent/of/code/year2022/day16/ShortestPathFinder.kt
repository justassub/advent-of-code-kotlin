package advent.of.code.year2022.day16

data class InternalCache(
    val startPosition: String,
    val minutesLeft: Int,
    val alreadyOpened: MutableMap<String, Int>,
    val visisted: Set<String>
)

class ShortestPathFinder(private val data: Map<String, Valve>) {
    private val cache: MutableMap<InternalCache, Int> = mutableMapOf()

    fun funFindMaxFlowes(start: String): Int {
        return funFindMaxFlowes(start, 30, mutableMapOf(), emptySet())
    }

    private fun funFindMaxFlowes(
        startPosition: String,
        minutesLeft: Int,
        alreadyOpened: MutableMap<String, Int>,
        visisted: Set<String>
    ): Int {
        val cacheKey = InternalCache(startPosition, minutesLeft, alreadyOpened, visisted)
        if (minutesLeft > 20 && cache.containsKey(cacheKey)) {
            return cache.getValue(cacheKey)
        }
        val currentValve = data.getValue(startPosition)
        if (minutesLeft < 1) {
            return alreadyOpened.values.sum()
        }

        if (currentValve.flowRate != 0 && startPosition !in alreadyOpened) {

            val result = maxOf(
                tryOpen(currentValve, minutesLeft - 1, alreadyOpened.toMutableMap()),
                currentValve.nearValves.filter { it !in visisted }
                    .maxOfOrNull { funFindMaxFlowes(it, minutesLeft - 1, alreadyOpened.toMutableMap(), visisted + it) }
                    ?: alreadyOpened.values.sum()
            )
            if (minutesLeft > 10) {
                cache[cacheKey] = result
            }
            return result
        }
        val result = currentValve.nearValves.filter { it !in visisted }.maxOfOrNull {
            funFindMaxFlowes(
                it,
                minutesLeft - 1,
                alreadyOpened,
                visisted + it
            )
        } ?: alreadyOpened.values.sum()
        if (minutesLeft > 10) {
            cache[cacheKey] = result
        }
        return result
    }

    private fun tryOpen(
        currentValve: Valve,
        minutesLeft: Int,
        alreadyOpened: MutableMap<String, Int>
    ): Int {
        alreadyOpened[currentValve.name] = currentValve.flowRate * minutesLeft
        return currentValve.nearValves
            .maxOf { funFindMaxFlowes(it, minutesLeft - 1, alreadyOpened, emptySet()) }
    }
}
