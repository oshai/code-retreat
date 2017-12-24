package bowling

import com.google.common.truth.Truth
import com.google.common.truth.Truth.*
import org.junit.Assert.*
import org.junit.Test

class BowlingTest {

    private val tested = Bowling()

    @Test fun `zero game`() {
        val score: Int = tested.play(List(20) { 0 })
        assertThat(score).isEqualTo(0)
    }
    @Test fun `all throws one`() {
        val score: Int = tested.play(List(20) { 1 })
        assertThat(score).isEqualTo(20)
    }
    @Test fun `all one , just one strike`() {
        val rolls: List<Int?> = List(18) { 1 }
        val moreRolls: List<Int?> = listOf(10, null, 1, 1)
        val rollsWithStrike = rolls + moreRolls
        val score: Int = tested.play(rollsWithStrike)
        assertThat(score).isEqualTo(30)
    }
    @Test fun `all one , just one spare`() {
        val rolls: List<Int?> = List(18) { 1 }
        val moreRolls: List<Int?> = listOf(5, 5, 1)
        val rollsWithStrike = rolls + moreRolls
        val score: Int = tested.play(rollsWithStrike)
        assertThat(score).isEqualTo(29)
    }

}
