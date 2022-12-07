package advent.of.code.year2022.day7

object UnixFileStructureGenerator {
    fun generateUnixStructure(commands: List<UnixCommand>): MutableMap<String, UnixFile> {
        val folders = mutableMapOf("/" to UnixFile(null, "/", true))

        var currentDir = folders.getValue("/")

        for (command in commands) {
            when (command) {

                is CD -> {
                    currentDir = if (command.goto.endsWith("..")) {
                        if (currentDir.fileName == "/") {
                            currentDir
                        } else {
                            currentDir.parentFile!!
                        }

                    } else {
                        if (command.goto == "/") {
                            folders.getValue("/")
                        } else {
                            folders.getValue("${currentDir.generateAbsolutePath()}/${command.goto}")
                        }

                    }
                }

                is LS -> {
                    val files = createUnixDirectoriesAndAddFileToCurrentFile(currentDir, command.listFiles)
                    files.filter { it.isDir }
                        .forEach {
                            if (folders.containsKey(it.generateAbsolutePath())) {
                                throw IllegalArgumentException("Should not happen")
                            }
                            folders.putIfAbsent(it.generateAbsolutePath(), it)
                        }
                }
            }
        }
        return folders
    }

    private fun createUnixDirectoriesAndAddFileToCurrentFile(
        currentDir: UnixFile,
        listFiles: List<String>
    ): List<UnixFile> {
        val newFiles = listFiles.map { createFile(currentDir, it) }
        currentDir.addFiles(newFiles)
        return newFiles
    }

    private fun createFile(currentDir: UnixFile, fileLine: String): UnixFile {
        return when {
            fileLine.startsWith("dir") -> UnixFile(currentDir, fileLine.removePrefix("dir "), true)
            else -> {
                val data = fileLine.split(" ")
                UnixFile(currentDir, data.last(), false, data.first().toLong())
            }
        }
    }
}