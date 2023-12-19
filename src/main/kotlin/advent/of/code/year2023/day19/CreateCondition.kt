package advent.of.code.year2023.day19


fun createRuleNameAndCondition(line: String): Pair<String, String> {
    val conditionName = line.split("{").first()
    val condition = line.split("{").last().replace("}", "")
    return conditionName to condition
}


fun createMultipleConditions(line: String): List<Rule> {
    return line.split(",")
        .map {
            if (it.contains(":")) {
                CustomRule(it)
            } else {
                SimpleRule(it)
            }
        }
}


