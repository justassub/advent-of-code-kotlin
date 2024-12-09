package advent.of.code.year2024.day9

import java.util.*

class Amphipod(val data: String) {

    var idNumber = 0
    val blockRepresentation = data
        .toCharArray()
        .map { it.digitToInt() }
        .withIndex()
        .mapNotNull { createFileSequanceWithInts(it) }


    fun compactBlocksFixed(): List<Int> {
        val blocks = LinkedList(blockRepresentation.flatten())
        val compact = mutableListOf<Int>()

        while (blocks.isNotEmpty()) {
            val element = blocks.removeFirst()
            if (element != null) {
                compact.add(element)
            } else {
                do {
                    if (blocks.isEmpty()) {
                        break
                    }
                    val nextElement = blocks.removeLast()
                    if (nextElement != null) {
                        compact.add(nextElement)
                    }
                } while (nextElement == null)
            }
        }
        return compact
    }

    fun compactBlocksFixedMoreStrick(): List<Int?> {
        TODO()
    }


    private fun createFileSequanceWithInts(pair: IndexedValue<Int>): List<Int?>? {

        return if (pair.index % 2 == 0) {
            generateSequence { idNumber }.take(pair.value).toList()
        } else {
            idNumber++
            if (pair.value == 0) {
                null
            } else {
                arrayOfNulls<Int>(pair.value).toList()
            }
        }
    }
}