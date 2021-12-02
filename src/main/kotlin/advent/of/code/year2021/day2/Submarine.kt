package advent.of.code.year2021.day2

open class Submarine {
    var horizontalPosition = 0;
    var debt = 0

    open fun moveForward(amount: Int) {
        horizontalPosition += amount
    }

    open fun moveUp(amount: Int) {
        debt -= amount
    }

    open fun moveDown(amount: Int) {
        debt += amount
    }
}
