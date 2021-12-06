package advent.of.code.year2021.day6

import java.util.*

class Ocean(var fish: Map<Int, Long>) {

    fun passDay() {
        val newPool = TreeMap<Int, Long>()
        fish.forEach {
            newPool[it.key - 1] = it.value
        }
        val newFishes = newPool.getOrDefault(SPAWN_NEW_FISH_AFTER, 0)
        newPool.remove(SPAWN_NEW_FISH_AFTER)
        newPool.merge(INTERNAL_TIMER_OF_REPRODUCED_FISH, newFishes, Long::plus)
        newPool[INTERNAL_TIMER_OF_NEW_FISH] = newFishes

        fish = newPool
    }

    companion object {
        const val INTERNAL_TIMER_OF_NEW_FISH = 8
        const val INTERNAL_TIMER_OF_REPRODUCED_FISH = 6
        const val SPAWN_NEW_FISH_AFTER = -1
    }
}