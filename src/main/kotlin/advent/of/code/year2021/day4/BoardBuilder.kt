package advent.of.code.year2021.day4

object BoardBuilder {
    fun buildBoardFromLines(lines: List<String>): Board {
        val rows = lines
            .map { line ->
                    line.split(" ")
                    .filter { it.isNotEmpty() }
                    .map { it.toInt() }
            }


        val columns = rows.indices
            .map { index -> rows.map { row -> row[index] } }

        return Board(
            rows = rows.map { Row(it.toSet()) },
            columns = columns.map { Row(it.toSet()) }
        )
    }
}