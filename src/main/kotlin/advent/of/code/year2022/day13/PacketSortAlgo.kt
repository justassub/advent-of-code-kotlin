package advent.of.code.year2022.day13

class PacketSortAlgo : Comparator<Packet> {

    override fun compare(o1: List<*>?, o2: List<*>?): Int {
        val pairs = o1!!.zip(o2!!)
        for ((el1, el2) in pairs) {
            val res = compareSingle(el1!!, el2!!)
            if (res != 0) {
                return res
            }
        }
        return o1.size.compareTo(o2.size)
    }


    private fun compareSingle(p1: Any, p2: Any): Int {
        return when {
            p1 is Int && p2 is Int ->
                (p1.compareTo(p2))

            p1 is List<*> && p2 is List<*> -> {
                compare(p1, p2)
            }

            p1 is List<*> && p2 is Int -> {
                compare(p1, listOf(p2))
            }

            p1 is Int && p2 is List<*> -> {
                compare(listOf(p1), p2)
            }

            else -> throw IllegalArgumentException("Should not happen")
        }
    }


}
