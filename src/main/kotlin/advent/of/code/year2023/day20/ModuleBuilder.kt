package advent.of.code.year2023.day20

object ModuleBuilder {

    fun buildModules(lines: List<String>): Collection<Module> {
        val splitLines = lines.map { it.split(" -> ") }
        val modules = splitLines
            .map { it.first() }
            .map { createModule(it) }
            .associateBy { it.getName() }

        splitLines
            .forEach { addDestinationsToModules(modules, it.first(), it.last()) }
        val allModules = modules.values

        allModules
            .filterIsInstance<ConjunctionModule>()
            .forEach { conjunctionModule ->
                conjunctionModule.setInputs(
                    allModules
                        .filter { it.getDestinations().contains(conjunctionModule) }
                        .associateWith { Pulse.LOW }
                        .toMutableMap()
                )
            }
        return allModules
    }

    private fun addDestinationsToModules(modules: Map<String, Module>, name: String, destinations: String) {
        val sanitizedName = name.replace("&", "").replace("%", "")

        val destinationModules = destinations.split(", ")
            .map { modules[it] ?: UntypedModule(it) }

        modules[sanitizedName]!!.setDestinations(destinationModules)
    }

    private fun createModule(data: String): Module {
        return when {
            data == "broadcaster" -> BroadCaster()
            data.startsWith("%") -> FlipFlopModule(data.drop(1))
            data.startsWith("&") -> ConjunctionModule(data.drop(1))
            else -> throw IllegalArgumentException("Bad input $data")
        }
    }
}