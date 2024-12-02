package advent.of.code.year2024.day2

import advent.of.code.ContentReader.readFileAsMultipleNumbersInLine

fun main() {
    val notValidatedReports = readFileAsMultipleNumbersInLine(2024, 2, " ")
        .map { Report(it) }


    val validatedReports = notValidatedReports.partition { it.validate() }

    val validReports = validatedReports.first
    println(validReports.count())

    val notValidReports = validatedReports.second
    println(validReports.count() + notValidReports.count { it.canReportBeFixedByChangingOneNumber() })

}

data class Report(val grades: List<Int>) {
    private val changesMustBeAtLeast = 1
    private val changesMustBeAtMost = 3

    fun validate(): Boolean {
        return if (grades.first() > grades[1]) {
            validateDecreasingNumbers()
        } else {
            validateIncreasingNumbers()
        }
    }

    fun canReportBeFixedByChangingOneNumber(): Boolean {
        return grades.indices.map { index ->
            grades.filterIndexed { i, _ -> i != index }
        }.any { Report(it).validate() }
    }

    private fun validateDecreasingNumbers(): Boolean {
        return grades.zipWithNext()
            .all { (a, b) -> (a - b) in changesMustBeAtLeast..changesMustBeAtMost }
    }

    private fun validateIncreasingNumbers(): Boolean {
        return grades.zipWithNext()
            .all { (a, b) -> (b - a) in changesMustBeAtLeast..changesMustBeAtMost }
    }
}
