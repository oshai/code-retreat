package bowling

class Bowling {
    fun play(rolls: List<Int?>): Int {
        return (0..9).sumBy { index ->
            val firstRoll = rolls[index * 2]
            val secondRoll = rolls[index * 2 + 1]
            val frameScore = (firstRoll!!) + (secondRoll ?: 0)
            var totalScore = frameScore
            if (firstRoll == 10) { //strike
                totalScore += rolls[index * 2 + 2]!! + rolls[index * 2 + 3]!! //TODO check case that index *2 + 2 is strike
            } else if (frameScore == 10) { //spare
                totalScore += rolls[index * 2 + 2]!!
            }
            totalScore
        }
    }
}
