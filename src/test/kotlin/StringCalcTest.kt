import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class StringCalcTest {

    val tested = StringCalc()

    @Test fun `test for empty string`() {
        val result = tested.add("")
        assertEquals(0, result)
    }
    @Test fun `test for simple add`() {
        val result = tested.add("1,2")
        assertEquals(3, result)
    }
    @Test fun `test for new line add`() {
        val result = tested.add("1\n3")
        assertEquals(4, result)
    }
    @Test fun `test for delimiters line`() {
        val result = tested.add("//;\n3;2")
        assertEquals(5, result)
    }
    @Test(expected = RuntimeException::class) fun `test for illegal sequence`() {
        tested.add("1,\n3")
    }
    @Test fun `test for negative numbers`() {
        try {
            tested.add("-1")
            Assert.fail()
        } catch(e: Exception) {
            assertEquals("negative not allowed: -1", e.message)
        }
    }
}
