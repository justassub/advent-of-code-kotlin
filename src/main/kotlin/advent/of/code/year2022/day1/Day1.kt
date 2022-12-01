package advent.of.code.year2022.day1

import advent.of.code.year2022.ContentReader2022

const val day = 1

fun List<Elf>.findElfWithMostWeight() = this.findNumberOfElfsWithMostWeight(1).first()

fun List<Elf>.findNumberOfElfsWithMostWeight(n: Int) = this.sortedByDescending { it.calculateTotalCalories() }.take(n)

fun main() {

    val data = ContentReader2022.readFileAsText(day)
    val elfs = ElfBuilder.buildElfs(data)

    val elfWithMostWeight = elfs.findElfWithMostWeight()
    println(elfWithMostWeight.calculateTotalCalories())

    val topThree = elfs.findNumberOfElfsWithMostWeight(3)
    println(topThree.sumOf { it.calculateTotalCalories() })


}
