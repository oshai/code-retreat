import com.google.common.base.CharMatcher
import com.google.common.base.Splitter

class StringCalc {
    fun add(numbers: String): Int {
        return when {
            numbers.isBlank() -> 0
            numbers.startsWith("//") -> {
                val split = numbers.split("\n")
                return add(listOf(split[0].removePrefix("//")), split[1])
            }
            else -> add(listOf("\n",","), numbers)
        }
    }

    private fun add(delim: List<String>, values: String): Int {
        val numbers = values.split(* (delim.toTypedArray())).map { it.toInt() }
        val negative = numbers.filter { it < 0 }
        if (negative.isNotEmpty()) {
            throw IllegalArgumentException("negative not allowed: ${negative.joinToString(",")}")
        }
        return numbers.sum()
    }
}
