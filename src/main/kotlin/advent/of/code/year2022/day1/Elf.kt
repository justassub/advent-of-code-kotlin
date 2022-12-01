package advent.of.code.year2022.day1

data class Elf(private val calories: List<Int>) {

    fun calculateTotalCalories() = calories.sum()
}
