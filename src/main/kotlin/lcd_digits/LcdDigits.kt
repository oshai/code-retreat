package lcd_digits

class LcdDigits {
    val digits: List<String> = listOf(
            """ . _
                .| |
                .|_|""",
            """ .
                .  |
                .  |""",
            """ . _
                . _|
                .|_ """,
            """ . _
                . _|
                . _|""",
            """ .
                .|_|
                .  |""",
            """ . _
                .|_
                . _|""",
            """ . _
                .|_
                .|_|""",
            """ . _
                .  |
                .  |""",
            """ . _
                .|_|
                .|_|""",
            """ . _
                .|_|
                . _|"""
    )
            .map { it.trimMargin(".") }

    fun getDisplay(i: Int): String {
        return (0..2).map { line ->
            val stringNum = i.toString()
            val lineString = stringNum.map { digit -> getLine(digit = digit - '0', line = line) }.joinToString(" ")
            lineString
        }.joinToString("\n")
    }

    private fun getLine(digit: Int, line: Int): String = digits[digit].split("\n")[line].padEnd(3)
}
