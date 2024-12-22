package advent.of.code.year2024.day22

import advent.of.code.ContentReader
import advent.of.code.util.printResult

fun main() {
    val secretNumbers = ContentReader.readFileAsNumbers(2024, 22)
        .map(::GenerateSecretClass)
    secretNumbers
        .sumOf { it.generateNumberPart1(2000) }
        .printResult()

}

class GenerateSecretClass(originalDigit: Int) {
    private var secretNumber: Long = originalDigit.toLong()


    fun generateNumberPart1(n: Int): Long {
        repeat(n) {
            step1()
            step2()
            step3()
        }
        return secretNumber
    }

    private fun step1() {
        mixNumbers(secretNumber * 64)
        prune()
    }

    private fun step2() {
        mixNumbers(secretNumber / 32)
        prune()
    }

    private fun step3() {
        mixNumbers(secretNumber * 2048)
        prune()
    }


    private fun mixNumbers(n: Long) {
        secretNumber = secretNumber xor n
    }

    private fun prune() {
        secretNumber %= 16777216
    }
}