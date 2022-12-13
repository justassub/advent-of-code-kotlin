package advent.of.code.year2022.day13


object PacketGenerator {

    fun parseList(packetLine: String): List<Any> {
        val result = parse(packetLine)
        return transformPackets(result!! as Packets.PacketList)
    }

    private fun transformPackets(packet: Packets.PacketList): List<Any> {

        val list = mutableListOf<Any>()

        packet.packets.forEach {
            when (it) {
                is Packets.PacketList -> list.add(transformPackets(it))
                is Packets.PacketValue -> list.add(it.packets)
            }

        }
        return list
    }

    private fun parse(str: String): Packets? {
        if (str.isEmpty()) {
            return null
        }
        if (str[0].isDigit()) {
            return Packets.PacketValue(str.toInt())
        }

        var bracketCount = 0
        var lastComma = 0

        val packets = mutableListOf<Packets?>()

        for ((i, c) in str.withIndex()) {
            if (c == '[') {
                bracketCount++
            } else if (c == ']') {
                bracketCount--
                if (bracketCount == 0) {
                    packets += parse(str.take(i).drop(lastComma + 1))
                }
            }
            if (c == ',') {
                if (bracketCount == 1) {
                    packets += parse(str.take(i).drop(lastComma + 1))
                    lastComma = i
                }
            }
        }

        return Packets.PacketList(packets.filterNotNull())
    }
}
sealed class Packets {
    data class PacketList(val packets: List<Packets>) : Packets()
    data class PacketValue(val packets: Int) : Packets()
}
