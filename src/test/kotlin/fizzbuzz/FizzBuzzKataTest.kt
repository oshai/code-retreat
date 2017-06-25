package fizzbuzz

import com.google.common.truth.Truth.*
import org.junit.Test

class FizzBuzzKataTest {
    private val tested = FizzBuzzKata()

    @Test fun `printing numbers without fizz buzz`(){
        assertThat(tested.generateFizzBuzz(2))
                .isEqualTo("2")
    }

    @Test fun `printing numbers with fizz`(){
        assertThat(tested.generateFizzBuzz(3))
                .isEqualTo("Fizz")
    }

    @Test fun `printing numbers with buzz`(){
        assertThat(tested.generateFizzBuzz(5))
                .isEqualTo("Buzz")
    }

    @Test fun `printing numbers with fizzbuzz`(){
        assertThat(tested.generateFizzBuzz(15))
                .isEqualTo("FizzBuzz")
    }
    @Test fun `printing sequence`(){
        assertThat(tested.generateFizzBuzzSequence(15))
                .isEqualTo((1..15).map {tested.generateFizzBuzz(it)})
    }
}
