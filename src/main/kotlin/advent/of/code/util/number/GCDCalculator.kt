package advent.of.code.util.number

object GCDCalculator {


    fun lcm(input: List<Long>): Long {
        var result = input[0]
        for (i in 1 until input.size) result = lcm(result, input[i])
        return result
    }

    private fun lcm(a: Long, b: Long): Long {
        return a * (b / gcd(a, b))
    }
    private fun gcd(a: Long, b: Long): Long {
        var a = a
        var b = b
        while (b > 0) {
            val temp = b
            b = a % b // % is remainder
            a = temp
        }
        return a
    }
}