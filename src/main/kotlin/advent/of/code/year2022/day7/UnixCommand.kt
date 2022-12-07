package advent.of.code.year2022.day7

sealed interface UnixCommand

data class CD(val goto: String) : UnixCommand

data class LS( val listFiles: List<String>) : UnixCommand