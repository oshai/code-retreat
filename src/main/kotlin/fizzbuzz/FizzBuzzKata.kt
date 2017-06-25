package fizzbuzz

class FizzBuzzKata {
    fun generateFizzBuzz(number: Int): String = when {
        number.isDividedBy(3) and number.isDividedBy(5) -> "FizzBuzz"
        number.isDividedBy(3) -> "Fizz"
        number.isDividedBy(5) -> "Buzz"
        else -> number.toString()
    }

    private fun Int.isDividedBy(dividor: Int) = this.rem(dividor) == 0

    fun generateFizzBuzzSequence(i: Int): List<String> =
            (1..i).map { generateFizzBuzz(it) }
}
