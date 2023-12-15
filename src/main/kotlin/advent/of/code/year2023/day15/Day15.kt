package advent.of.code.year2023.day15

import advent.of.code.ContentReader
import advent.of.code.util.printResult

typealias FocalLength = Int

const val minBox = 0
const val maxBox = 255
fun main() {
    val data = ContentReader.readFileAsText(2023, 15).split(",")


    data
        .sumOf { HashCalculatorDay15.calculateHash(0, it) }
        .printResult()

    val boxes = (minBox..maxBox).associateWith { mutableListOf<Pair<String, FocalLength>>() }


    data
        .forEach { processCreatingBoxes(boxes, it) }

    boxes
        .flatMap { it.value.mapIndexed { index, v -> (index + 1) * v.second * (it.key + 1) } }
        .sum()
        .printResult()
    println()


}

fun processCreatingBoxes(map: Map<Int, MutableList<Pair<String, FocalLength>>>, element: String) {
    if (element.contains("-")) {
        val removeElement = element.split("-").first()
        map.values
            .forEach { it.removeIf { e -> e.first == removeElement } }
    } else {
        val addElement = element.split("=").first()
        val addElementValue = element.split("=").last().toInt()

        val box = HashCalculatorDay15.calculateHash(0, addElement)
        if (box < minBox || box > maxBox) {
            throw IllegalArgumentException(" box is : $box")
        }
        val values = map[box]!!
        val replaceElement = values.find { it.first == addElement }
        if (replaceElement != null) {
            val index = values.indexOf(replaceElement)
            values.removeAt(index)
            values.add(index, addElement to addElementValue)
        } else {
            values.add(addElement to addElementValue)
        }
    }
}