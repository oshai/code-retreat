package stringcalc

import org.junit.Test

import com.google.common.truth.Truth.assertThat
import org.junit.Assert

//import com.google.common.truth.Truth8.assertThat // for assertions on Java 8 types

class StringCalculatorTest {

    private val tested: StringCalculator = StringCalculator()
    @Test(expected = RuntimeException::class)
    fun `add Illegal values should throw exception`() {
        tested.add("1,\n")
    }
    @Test
    fun `negative should throw exception with all numbers`() {
        try {
            tested.add("-1,-2")
            Assert.fail()
        } catch(e: Exception) {
            assertThat(e.message).contains("-1, -2")
        }
    }
    @Test
    fun add1() {
        assertThat(tested.add("")).isEqualTo(0)
        assertThat(tested.add("1")).isEqualTo(1)
        assertThat(tested.add("1,2")).isEqualTo(3)
        assertThat(tested.add("1\n2,3")).isEqualTo(6)
        assertThat(tested.add("//;\n1;2")).isEqualTo(3)
        assertThat(tested.add("1001,2")).isEqualTo(2)
        assertThat(tested.add("//[***]\n1***2***3")).isEqualTo(6)
        assertThat(tested.add("//[*][%]\n1*2%3")).isEqualTo(6)
    }

}
