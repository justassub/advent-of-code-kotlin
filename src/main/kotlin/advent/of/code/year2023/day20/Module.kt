package advent.of.code.year2023.day20


enum class Pulse {
    HIGH, LOW
}

enum class ModuleStatus {
    ON, OFF
}

interface Module {
    fun send(pulse: Pulse, input: Module): List<Pair<Module, Pulse>>
    fun getName(): String
    fun setDestinations(modules: List<Module>)

    fun getHighPulseCount(): Int
    fun getLowPulseCount(): Int
    fun getDestinations(): List<Module>
}

class FlipFlopModule(private val name: String) : Module {
    private lateinit var destinations: List<Module>
    var status = ModuleStatus.OFF
    private var highPulseCount: Int = 0
    private var lowPulseCount: Int = 0

    override fun send(pulse: Pulse, input: Module): List<Pair<Module, Pulse>> {
        return when (pulse) {
            Pulse.HIGH -> emptyList()
            Pulse.LOW -> processLowStatus()
        }
    }

    private fun processHighStatus(): List<Pair<Module, Pulse>> {
        highPulseCount += destinations.size
        return destinations.map { it to Pulse.HIGH }
    }

    private fun processLowStatus(): List<Pair<Module, Pulse>> {
        return if (status == ModuleStatus.OFF) {
            status = ModuleStatus.ON
            highPulseCount += destinations.size
            destinations.map { it to (Pulse.HIGH) }
        } else {
            status = ModuleStatus.OFF
            lowPulseCount += destinations.size
            destinations.map { it to (Pulse.LOW) }
        }
    }

    override fun getName(): String {
        return name
    }

    override fun setDestinations(modules: List<Module>) {
        destinations = modules
    }

    override fun getHighPulseCount(): Int {
        return highPulseCount
    }

    override fun getLowPulseCount(): Int {
        return lowPulseCount
    }

    override fun getDestinations(): List<Module> {
        return destinations
    }

}

class ConjunctionModule(private val name: String) : Module {
    private lateinit var destinations: List<Module>

    private lateinit var inputs: MutableMap<Module, Pulse>

    private var highPulseCount: Int = 0
    private var lowPulseCount: Int = 0

    override fun send(pulse: Pulse, input: Module): List<Pair<Module, Pulse>> {
        inputs[input] = pulse
        return if (inputs.any { it.value == Pulse.LOW }) {
            highPulseCount += destinations.size
            destinations.map { it to Pulse.HIGH }
        } else {
            lowPulseCount += destinations.size
            destinations.map { it to Pulse.LOW }
        }
    }

    override fun getName(): String {
        return name
    }

    override fun setDestinations(modules: List<Module>) {
        destinations = modules
    }

    override fun getHighPulseCount(): Int {
        return highPulseCount
    }

    override fun getLowPulseCount(): Int {
        return lowPulseCount
    }

    override fun getDestinations(): List<Module> {
        return destinations
    }

    fun setInputs(inputs: MutableMap<Module, Pulse>) {
        this.inputs = inputs
    }
}

class BroadCaster : Module {
    private lateinit var destinations: List<Module>
    private var highPulseCount: Int = 0
    private var lowPulseCount: Int = 0

    override fun send(pulse: Pulse, input: Module): List<Pair<Module, Pulse>> {
        when (pulse) {
            Pulse.HIGH -> highPulseCount += destinations.size
            Pulse.LOW -> lowPulseCount += destinations.size
        }

        return destinations.map { it to pulse }
    }

    override fun getName(): String {
        return "broadcaster"
    }

    override fun setDestinations(modules: List<Module>) {
        destinations = modules
    }

    override fun getHighPulseCount(): Int {
        return highPulseCount
    }

    override fun getLowPulseCount(): Int {
        return lowPulseCount
    }

    override fun getDestinations(): List<Module> {
        return destinations
    }
}

class UntypedModule(private val name:String) : Module {
    override fun send(pulse: Pulse, input: Module): List<Pair<Module, Pulse>> {
        return emptyList()
    }

    override fun getName(): String {
        return name
    }

    override fun setDestinations(modules: List<Module>) {

    }

    override fun getHighPulseCount(): Int {
        return 0
    }

    override fun getLowPulseCount(): Int {
        return 0
    }

    override fun getDestinations(): List<Module> {
        return emptyList()
    }

}