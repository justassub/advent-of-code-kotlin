package advent.of.code.year2021.day6

class Fish(var internalTimer: Int) {
    private var producedFish = mutableListOf<Fish>()

    companion object {
        const val INTERNAL_TIMER_OF_NEW_FISH = 8
        const val INTERNAL_TIMER_OF_REPRODUCED_FISH = 6
        const val SPAWN_NEW_FISH_AFTER = 0
    }

    fun spendDay() {
        producedFish.forEach { it.spendDay() }
        if (internalTimer == SPAWN_NEW_FISH_AFTER) {
            internalTimer = INTERNAL_TIMER_OF_REPRODUCED_FISH
            producedFish.add(Fish(INTERNAL_TIMER_OF_NEW_FISH))
        }else{
            internalTimer--
        }
    }

    fun countYourselfAndChildren(): Long {
        return 1 + producedFish.sumOf { it.countYourselfAndChildren() }
    }
}