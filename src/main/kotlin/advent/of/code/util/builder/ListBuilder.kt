package advent.of.code.util.builder


object ListBuilder {

    fun buildPossibleCombinationsFromString(
        input: String,
        separator: String,
        possibleValues: List<Any>,
        addBetweenValues: String = "|"
    ): List<String> {

        return if (input.contains(separator)) {
            return possibleValues
                .map { input.replaceFirst(separator, "$addBetweenValues$it$addBetweenValues") }
                .flatMap { buildPossibleCombinationsFromString(it, separator, possibleValues) }
        } else {
            listOf(input)
        }
    }
}