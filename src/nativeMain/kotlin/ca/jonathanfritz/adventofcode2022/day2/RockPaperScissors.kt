package ca.jonathanfritz.adventofcode2022.day2

class RockPaperScissors {

    fun tournamentScore(rounds: List<String>): Int {
        return rounds.filter { it.isNotBlank() }.sumOf {
            val chars = it.split(" ")
            scoreRound(Shape.fromString(chars[0]), Shape.fromString(chars[1]))
        }
    }

    // The score for a single round is the score for the shape you selected plus the score for the outcome of the round
    private fun scoreRound(theirs: Shape, mine: Shape): Int = mine.score + getOutcome(mine, theirs).score

    private fun getOutcome(mine: Shape, theirs: Shape): Outcome {
        // If both players choose the same shape, the round instead ends in a draw.
        if (mine == theirs) {
            return Outcome.DRAW
        }

        // Rock defeats Scissors, Scissors defeats Paper, and Paper defeats Rock.
        if ((mine == Shape.ROCK && theirs == Shape.SCISSORS) ||
            (mine == Shape.PAPER && theirs == Shape.ROCK) ||
            (mine == Shape.SCISSORS && theirs == Shape.PAPER)
        ) {
            return Outcome.WIN
        }
        return Outcome.LOSE
    }

    enum class Shape(vararg val chars: String, val score: Int) {
        ROCK("A", "X", score = 1),
        PAPER("B", "Y", score = 2),
        SCISSORS("C", "Z", score = 3);

        companion object {
            fun fromString(str: String): Shape {
                return RockPaperScissors.Shape.values().first { it.chars.contains(str.trim().uppercase()) }
            }
        }
    }

    enum class Outcome(val score: Int) {
        // 0 if you lost, 3 if the round was a draw, and 6 if you won
        WIN(6),
        LOSE(0),
        DRAW(3)
    }
}