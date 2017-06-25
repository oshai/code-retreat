package stringcalc

class StringCalculator(val logger: ILogger, val webService: IWebService) {
    fun add(lines: String): Int {
        try {
            val sum = addInternal(lines)
            logger.lines = "sum is $sum"
            return sum
        } catch(e: Exception) {
            webService.send("add method failed: ${e.javaClass.simpleName} ${e.message} with input: '$lines'")
            throw e
        }
    }

    private fun addInternal(lines: String): Int {
        if (lines.isBlank()) {
            return 0
        }
        var (delimiters, linesToCalc) = splitDelimitersAndCalculated(lines)
        val numbers = parseNumbers(linesToCalc, delimiters)
        validateNumbers(numbers)
        return numbers.sum()
    }

    private fun validateNumbers(numbers: List<Int>) {
        val negatives = numbers.filter { it < 0 }
        if (negatives.isNotEmpty()) {
            throw RuntimeException("found negative numbers $negatives")
        }
    }

    private fun parseNumbers(linesToCalc: String, delimiters: Array<String>): List<Int> {
        val numbers = linesToCalc.split(delimiters = * delimiters)
                .map { it.toInt() }
        return numbers.filter { it <= 1000 }
    }

    private fun splitDelimitersAndCalculated(lines: String): Pair<Array<String>, String> {
        var delimiters = arrayOf(",", "\n")
        var linesToCalc = lines
        if (!lines.startsWith("//")) {
            return Pair(delimiters, linesToCalc)
        }
        var delims = lines.substringBefore("\n").substringAfter("//")
        var delimsAggregator = mutableListOf<String>()
        if (delims.contains("[")) {
            while (delims.contains("[")) {
                delimsAggregator.add(delims.substringAfter("[").substringBefore("]"))
                delims = delims.substringAfter("]")
            }
        } else {
            delimsAggregator.add(delims)
        }
        delimiters = delimsAggregator.toTypedArray()
        linesToCalc = lines.substringAfter("\n")
        return Pair(delimiters, linesToCalc)
    }
}

interface IWebService {
 fun send(message: String)
}

interface ILogger {
    abstract var lines: String
}
