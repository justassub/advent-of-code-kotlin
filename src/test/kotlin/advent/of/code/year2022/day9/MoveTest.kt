package advent.of.code.year2022.day9

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MoveTest {

    @Test
    fun `Master should be able to move all locations`() {
        val master = Follower(null)
        master.moveStep(Direction.DOWN)
        master.moveStep(Direction.DOWN)
        master.moveStep(Direction.RIGHT)
        master.moveStep(Direction.RIGHT)
        master.moveStep(Direction.UP)
        master.moveStep(Direction.LEFT)

        Assertions.assertEquals(Position(1, -1), master.getCurrentPosition())
    }

    @Test
    fun `Follower should not move if master is same position`() {
        val master = Follower(null)
        val follower = Follower(master)
        val followerLocation = follower.moveStep(Direction.UP)
        Assertions.assertEquals(Position(0, 0), followerLocation)
    }

    @Test
    fun `Follower should not move if master is very close position`() {
        val master = Follower(null)
        val follower = Follower(master)
        master.moveStep(Direction.RIGHT)
        master.moveStep(Direction.UP)
        val followerLocation = follower.moveStep(Direction.UP)
        Assertions.assertEquals(Position(0, 0), followerLocation)
    }
}
