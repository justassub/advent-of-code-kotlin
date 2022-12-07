package advent.of.code.year2022.day7

class UnixFile(val parentFile: UnixFile?, val fileName: String, val isDir: Boolean, private val initialSize: Long = 0) {
    private val otherFilesWithin = mutableListOf<UnixFile>()

    fun addFiles(files: Collection<UnixFile>) {
        files.forEach { addFile(it) }
    }

    private fun addFile(unixFile: UnixFile) {
        if (!isDir) {
            throw IllegalArgumentException("It is not allowed to add file into file")
        }
        otherFilesWithin.add(unixFile)
    }

    fun countTotalSize(): Long {
        return if (!isDir) {
            initialSize
        } else {
            otherFilesWithin.sumOf { it.countTotalSize() }
        }
    }

    fun generateAbsolutePath(): String {
        return if (parentFile == null) {
            "/"
        } else {
            "${parentFile.generateAbsolutePath()}/${this.fileName}"
        }
    }
}