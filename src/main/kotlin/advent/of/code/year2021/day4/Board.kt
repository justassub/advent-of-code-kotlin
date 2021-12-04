package advent.of.code.year2021.day4

class Board(private val rows: List<Row>, private val columns: List<Row>) {

    fun playNumberAndCheckIfWinners(number: Int): Pair<Boolean, Board> {
        val rowsWinner = rows.map { it.addNumberAndSayIfWon(number) }.any { it }
        val columnsWinner = columns.map { it.addNumberAndSayIfWon(number) }.any { it }
        return Pair(rowsWinner || columnsWinner, this)
    }

    fun sumUnmarkedNumbers(): Int {
        val sumOfUnmarkedRows = rows.flatMap {
            it.getUnmarkedNumbers()
        }.sum()
        val sumOfUnmarkedColumns = columns.flatMap {
            it.getUnmarkedNumbers()
        }.sum()
        return (sumOfUnmarkedRows + sumOfUnmarkedColumns) / 2
    }
}