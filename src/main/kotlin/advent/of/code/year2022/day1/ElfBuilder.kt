package advent.of.code.year2022.day1

object ElfBuilder {

    fun buildElfs(text: String): List<Elf> {
        return text
            .trim()
            .split("\n\n")
            .map { it.trim().split("\n") }
            .map { strings -> strings.filter { it.isNotBlank() }.map { it.trim().toInt() } }
            .map { Elf(it) }
    }

}
