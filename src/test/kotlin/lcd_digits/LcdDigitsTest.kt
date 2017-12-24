package lcd_digits

import com.google.common.truth.Truth
import org.junit.Test

class LcdDigitsTest {

    private val tested = LcdDigits()
    @Test fun `test zero digit`() {
        Truth.assertThat(tested.getDisplay(0))
                .isEqualTo(" _ \n" +
                                    "| |\n" +
                                    "|_|"
                                )
    }

    @Test fun `test 8 digit`() {
        Truth.assertThat(tested.getDisplay(8))
                .isEqualTo(" _ \n" +
                                    "|_|\n" +
                                    "|_|"
                                )
    }

    @Test fun `test 910`() {
        val result = tested.getDisplay(910)
        println(result)
        Truth.assertThat(result)
                .isEqualTo(" _       _ \n" +
                                    "|_|   | | |\n" +
                                    " _|   | |_|"
                                )
    }
}


