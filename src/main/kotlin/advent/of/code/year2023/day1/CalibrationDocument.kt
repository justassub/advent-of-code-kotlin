package advent.of.code.year2023.day1

class CalibrationDocument(private val document: String) {

    fun calculateCalibrationValue(): Int {
        val firstDigit = document.first { it.isDigit() }

        val secondDigit = document.last { it.isDigit() }
        return "$firstDigit$secondDigit".toInt()
    }
}