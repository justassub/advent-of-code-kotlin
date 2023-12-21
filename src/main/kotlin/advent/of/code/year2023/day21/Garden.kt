package advent.of.code.year2023.day21

import advent.of.code.util.Point
import advent.of.code.util.findNeighbours

class Garden(private val gardenData: Map<Point, Char>) {


    private val start = gardenData.filter { it.value == 'S' }.keys
    private val flowers = gardenData.filter { it.value == '#' }


    fun walkSteps(steps: Int): Set<Point> {

        return (1..steps)
            .fold(start) { acc, _ ->
                acc.flatMap { it.findNeighbours() }.filterNot { flowers.containsKey(it) }.toSet()
            }
    }
}