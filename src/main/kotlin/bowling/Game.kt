package bowling

import com.google.common.truth.Truth
import org.junit.Test

class Game {

    private val rolls: MutableList<Int> = mutableListOf()

    fun roll(score: Int) {
        rolls.add(score)
    }

    fun score(): Int {
        var sum: Int = 0
        var rollIndex = 0
        (1..10).forEach {
            when {
            //strike
                rolls[rollIndex] == 10 -> {
                    sum += 10 + rolls[rollIndex + 1] + rolls[rollIndex + 2]
                    rollIndex += 1
                }
            //spare
                rolls[rollIndex] + rolls[rollIndex + 1] == 10 -> {
                    sum += 10 + rolls[rollIndex + 2]
                    rollIndex += 2
                }
                else -> {
                    sum += rolls[rollIndex] + rolls[rollIndex + 1]
                    rollIndex += 2
                }
            }
        }
        return sum
    }

}


class GameTest {

    private val tested = Game()

    @Test fun `gutter game`() {
        rollFew(20, 0)
        Truth.assertThat(tested.score()).isEqualTo(0)
    }
    @Test fun `perfect game`() {
        rollFew(12, 10)
        Truth.assertThat(tested.score()).isEqualTo(300)
    }

    @Test fun `spare in frame - should bonus next throw`() {
        rollFew(2, 5)
        rollFew(2, 1)
        rollFew(16, 0)
        Truth.assertThat(tested.score()).isEqualTo(13)
    }

    @Test fun `strike in frame - should bonus next 2 throws`() {
        rollFew(1, 10)
        rollFew(2, 1)
        rollFew(16, 0)
        Truth.assertThat(tested.score()).isEqualTo(14)
    }

    @Test fun `all rolls is 1`() {
        rollFew(20, 1)
        Truth.assertThat(tested.score()).isEqualTo(20)
    }


    private fun rollFew(numOfRolls: Int, score: Int) {
        (1..numOfRolls).forEach {
            tested.roll(score)
        }
    }


}
