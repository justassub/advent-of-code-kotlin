package advent.of.code.year2022.day4

data class SanitizerElf(val startSection: Int, val endSection: Int)


fun Pair<SanitizerElf, SanitizerElf>.overlapFully(): Boolean {
    if (startsOrEndsSame()) {
        return true
    }
    val (firstElf, secondElf) = when (this.first.startSection < this.second.startSection) {
        true -> Pair(this.first, this.second)
        false -> Pair(this.second, this.first)
    }

    return firstElf.endSection > secondElf.endSection
}


fun Pair<SanitizerElf, SanitizerElf>.overlap(): Boolean {
    if (startsOrEndsSame()) {
        return true
    }
    val (firstElf, secondElf) = when (this.first.startSection < this.second.startSection) {
        true -> Pair(this.first, this.second)
        false -> Pair(this.second, this.first)
    }
    return firstElf.endSection >= secondElf.startSection
}

private fun Pair<SanitizerElf, SanitizerElf>.startsOrEndsSame(): Boolean {
    if (this.first.startSection == this.second.startSection || this.first.endSection == this.second.endSection) {
        return true
    }
    return false
}