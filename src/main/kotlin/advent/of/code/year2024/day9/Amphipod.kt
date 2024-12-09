package advent.of.code.year2024.day9

import java.util.*

class Amphipod(val data: String) {

    var idNumber = 0
    val blockRepresentation = data
        .toCharArray()
        .map { it.digitToInt() }
        .withIndex()
        .mapNotNull { createFileSequanceWithInts(it) }


    fun compactBlocksFixedMoreStrick(): List<Int?> {
        val list = blockRepresentation.map { it.toMutableList() }
        val blocks = LinkedList(list)

        while (blocks.isNotEmpty()) {
            val nextElement: MutableList<Int?> = blocks.removeLastOrNull() ?: break
            if (!nextElement.contains(null)) {
                val replaceElements = list.firstOrNull { it.filter { e -> e == null }.size >= nextElement.size }
                if (replaceElements == null) {
                    continue
                }
                if (list.indexOf(replaceElements) > list.indexOf(nextElement)) {
                    continue
                }
                val firstNullIndex = replaceElements.indexOf(null)
                val lastNullIndex = firstNullIndex + nextElement.size - 1
                val digits = nextElement.first()
                (firstNullIndex..lastNullIndex).forEach {
                    replaceElements[it] = digits
                }
                // make elements null in nextElement list
                nextElement.indices.forEach {
                    nextElement[it] = null
                }

            }
        }

        return list.flatten()
    }

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