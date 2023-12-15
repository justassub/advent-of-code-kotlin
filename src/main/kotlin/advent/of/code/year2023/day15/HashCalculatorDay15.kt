package advent.of.code.year2023.day15

object HashCalculatorDay15 {

    fun calculateHash(initialValue: Int, text: String): Int {
        return text.fold(initialValue) { acc, c -> calculateCharHash(acc, c) }
    }

    private fun calculateCharHash(initialValue: Int, c: Char): Int {
        return ((initialValue + c.code) * 17) % 256
    }
}