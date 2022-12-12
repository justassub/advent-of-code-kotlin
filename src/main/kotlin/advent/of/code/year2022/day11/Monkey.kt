package advent.of.code.year2022.day11

import java.util.*

class Monkey(private val operation: ((Long) -> Long),
             private val testCase: ((Long) -> Int)) {
    private var inspectedItems: Long = 0;

    private var items = LinkedList<Long>()


    fun addItem(item: Long) {
        items.add(item)
    }

    fun addItems(items: Collection<Long>) {
        items.forEach { addItem(it) }
    }

    fun getInspectedItemCount(): Long {
        return inspectedItems
    }

    fun inpectAll(newItemLevelChanger: ((Long) -> Long)): Map<Int, List<Long>> {
        val inspected = items.map { inspectFirst(it, newItemLevelChanger) }.groupBy({ it.first }, { it.second })
        items.clear()
        return inspected
    }

    private fun inspectFirst(item: Long, newItemLevelChanger: ((Long) -> Long)): Pair<Int, Long> {
        inspectedItems++
        val newItemValue = operation.invoke(item)
        val downSizedItem = newItemLevelChanger.invoke(newItemValue)
        val throwTo = testCase.invoke(downSizedItem)
        return Pair(throwTo, downSizedItem)
    }
}