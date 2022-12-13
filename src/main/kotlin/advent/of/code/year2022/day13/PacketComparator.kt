package advent.of.code.year2022.day13

import kotlin.math.max

typealias Packet = List<*>

object PacketComparator {

    fun compare(packet1: Packet, packet2: Packet): Boolean {

        repeat(max(packet1.size, packet2.size)) {
            val p1e = packet1.getOrNull(it)
            val p2e = packet2.getOrNull(it)

            when {
                p1e == null -> return true
                p2e == null -> return false
                p1e == p2e -> {}// IGNORE
                else -> return compareObjects(p1e, p2e)
            }
        }
        return true
    }

    private fun compareObjects(o1: Any, o2: Any): Boolean {
        return when {
            o1 is Int && o2 is Int -> o1 < o2
            o1 is Int && o2 is List<*> -> compare(listOf(o1), o2)
            o1 is List<*> && o2 is Int -> compare(o1, listOf(o2))
            o1 is List<*> && o2 is List<*> -> compare(o1, o2)
            else -> throw IllegalArgumentException("Should not happen $o1,$o2")
        }
    }


}
