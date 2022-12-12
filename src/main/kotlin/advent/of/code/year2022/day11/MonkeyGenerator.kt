package advent.of.code.year2022.day11

object MonkeyGenerator {

    fun generateTest(): Map<Int, Monkey> {
        val monkey0 = Monkey(
            operation = { item -> item * 19 },
            { item -> if (item % 23 == 0L) 2 else 3 }
        )
        monkey0.addMultipleDefaultItems("79, 98")

        val monkey1 = Monkey(
            operation = { item -> item + 6 },
            { item -> if (item % 19 == 0L) 2 else 0 }
        )
        monkey1.addMultipleDefaultItems("54, 65, 75, 74")

        val monkey2 = Monkey(
            operation = { item -> item * item },
            { item -> if (item % 13 == 0L) 1 else 3 }
        )
        monkey2.addMultipleDefaultItems("79, 60, 97")

        val monkey3 = Monkey(
            operation = { item -> item + 3 },
            { item -> if (item % 17 == 0L) 0 else 1 }
        )
        monkey3.addMultipleDefaultItems("74")

        return mapOf(
            0 to monkey0,
            1 to monkey1,
            2 to monkey2,
            3 to monkey3
        )
    }

    fun generateDefaultMonkeys(): Map<Int, Monkey> {
        val monkey0 = Monkey(
            operation = { item -> item * 17 },
            { item -> if (item % 3 == 0L) 3 else 6 },
        )
        monkey0.addMultipleDefaultItems("59, 65, 86, 56, 74, 57, 56")

        val monkey1 = Monkey(
            operation = { item -> item + 2 },
            { item -> if (item % 13 == 0L) 3 else 0 },
        )
        monkey1.addMultipleDefaultItems("63, 83, 50, 63, 56")

        val monkey2 = Monkey(
            operation = { item -> item + 1 },
            { item -> if (item % 2 == 0L) 0 else 1 },
        )
        monkey2.addMultipleDefaultItems("93, 79, 74, 55")

        val monkey3 = Monkey(
            operation = { item -> item + 7 },
            { item -> if (item % 11 == 0L) 6 else 7 },
        )
        monkey3.addMultipleDefaultItems("86, 61, 67, 88, 94, 69, 56, 91")

        val monkey4 = Monkey(
            operation = { item -> item * item },
            { item -> if (item % 19 == 0L) 2 else 5 },
        )
        monkey4.addMultipleDefaultItems("76, 50, 51")

        val monkey5 = Monkey(
            operation = { item -> item + 8 },
            { item -> if (item % 17 == 0L) 2 else 1 },
        )
        monkey5.addMultipleDefaultItems("77, 76")

        val monkey6 = Monkey(
            operation = { item -> item * 2 },
            { item -> if (item % 5 == 0L) 4 else 7 },
        )
        monkey6.addItem(74L)

        val monkey7 = Monkey(
            operation = { item -> item + 6 },
            { item -> if (item % 7 == 0L) 4 else 5 },
        )
        monkey7.addMultipleDefaultItems(" 86, 85, 52, 86, 91, 95")

        return mapOf(
            0 to monkey0,
            1 to monkey1,
            2 to monkey2,
            3 to monkey3,
            4 to monkey4,
            5 to monkey5,
            6 to monkey6,
            7 to monkey7
        ).toSortedMap()

    }
}

fun Monkey.addMultipleDefaultItems(items: String) {
    items.split(",").map { it.trim().toLong() }.forEach { addItem(it) }
}