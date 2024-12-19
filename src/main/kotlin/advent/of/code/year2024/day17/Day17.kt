package advent.of.code.year2024.day17

fun main() {
    val registrarA = Registrar(30118712)
    val registrarB = Registrar(0)
    val registrarC = Registrar(0)

    val program = listOf(2,4,1,3,7,5,4,2,0,3,1,5,5,5,3,0)

    val intComputer = IntComputer(registrarA, registrarB, registrarC)
    intComputer.runPrograms(program)
    println(intComputer.output.joinToString(","))
}