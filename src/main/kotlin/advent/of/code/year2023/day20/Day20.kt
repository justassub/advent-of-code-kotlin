package advent.of.code.year2023.day20

import advent.of.code.ContentReader
import java.util.*


fun main() {
    val modules = ContentReader.readFileAsLines(2023, 20)
        .let { ModuleBuilder.buildModules(it) }


    val broadCaster = modules.find { it is BroadCaster }!!

    val repeatCount = 1000
    repeat(repeatCount) {
        pushButton(broadCaster)
    }

    val (high, low) = calculateHighAndLowCounts(modules)

    println(high * (low + repeatCount))

}


private fun pushButton(broadCaster: Module) {
    val queue = LinkedList(listOf(Triple(broadCaster, broadCaster, Pulse.LOW)))


    while (queue.isNotEmpty()) {
        val (destination, input, pulse) = queue.poll()
        val results = destination.send(pulse, input)
        results
            .map { Triple(it.first, destination, it.second) }
            .let { queue.addAll(it) }
    }
}

private fun calculateHighAndLowCounts(modules: Collection<Module>): Pair<Int, Int> {
    val distinctModules = (modules + modules.flatMap { it.getDestinations() }.distinct())
        .filterNot { it is UntypedModule }
        .distinctBy { it.getName() }


    val highPulses = distinctModules.sumOf { it.getHighPulseCount() }
    val lowPulses = distinctModules.sumOf { it.getLowPulseCount() }
    return Pair(highPulses, lowPulses)
}

