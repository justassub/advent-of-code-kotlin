package advent.of.code.year2022.day7

object UnixCommandGenerator {

    fun generateUnixCommands(lines: List<String>): MutableList<UnixCommand> {
        return generateUnixCommandsInternal(lines, mutableListOf())
    }

    private fun generateUnixCommandsInternal(
        lines: List<String>,
        createdCommands: MutableList<UnixCommand>
    ): MutableList<UnixCommand> {
        if (lines.isEmpty()) {
            return createdCommands
        } else {
            val nextLine = lines.first()
            return when {
                nextLine.contains("cd") -> {
                    createdCommands.add(generateCD(nextLine))
                    generateUnixCommandsInternal(lines.drop(1), createdCommands)
                }

                nextLine.contains("ls") -> {
                    val files = lines.drop(1).takeWhile { !it.contains("$") }
                    createdCommands.add(generateLS(files))
                    generateUnixCommandsInternal(lines.drop(1 + files.size), createdCommands)
                }

                else -> throw IllegalArgumentException("Should not reach this line: $nextLine")
            }
        }
    }

    private fun generateCD(line: String): UnixCommand {
        val goto = line.split(" cd ").last()
        return CD(goto)
    }

    private fun generateLS(lines: List<String>): UnixCommand {
        return LS(lines)
    }

}