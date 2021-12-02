package advent.of.code.year2021.day2

class SubmarineWithAim : Submarine() {
    var aim = 0
    override fun moveForward(amount: Int) {
        super.moveForward(amount)
        debt += aim * amount
    }

    override fun moveUp(amount: Int) {
        this.aim -= amount
    }

    override fun moveDown(amount: Int) {
        this.aim += amount
    }
}