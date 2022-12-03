package ca.jonathanfritz.adventofcode2022.day2

class RockPaperScissorsCheating {
    fun tournamentScore(rounds: List<String>): Int {
        return rounds.filter { it.isNotBlank() }.sumOf {
            val chars = it.split(" ")
            val opponentShape = Shape.fromString(chars[0])
            scoreRound(opponentShape, Outcome.fromString(chars[1]))
        }
    }

    private fun scoreRound(theirs: Shape, desiredOutcome: Outcome): Int {
        val mine = if (desiredOutcome == Outcome.DRAW) {
            theirs
        } else {
            // Rock defeats Scissors, Scissors defeats Paper, and Paper defeats Rock.
            when (theirs) {
                Shape.ROCK -> if (desiredOutcome == Outcome.WIN) Shape.PAPER else Shape.SCISSORS
                Shape.PAPER -> if (desiredOutcome == Outcome.WIN) Shape.SCISSORS else Shape.ROCK
                Shape.SCISSORS -> if (desiredOutcome == Outcome.WIN) Shape.ROCK else Shape.PAPER
            }
        }

        // The score for a single round is the score for the shape you selected plus the score for the outcome of the round
        return mine.score + desiredOutcome.score
    }

    enum class Shape(val char: String, val score: Int) {
        // Shape scores: 1 for Rock, 2 for Paper, and 3 for Scissors
        ROCK("A", score = 1),
        PAPER("B", score = 2),
        SCISSORS("C", score = 3);

        companion object {
            fun fromString(str: String): Shape {
                return Shape.values().first { it.char == str.trim().uppercase() }
            }
        }
    }

    enum class Outcome(val char: String, val score: Int) {
        // X means you need to lose, Y means you need to end the round in a draw, and Z means you need to win
        // 0 if you lost, 3 if the round was a draw, and 6 if you won
        WIN("Z", 6),
        LOSE("X", 0),
        DRAW("Y", 3);

        companion object {
            fun fromString(str: String): Outcome {
                return Outcome.values().first { it.char == str.trim().uppercase() }
            }
        }
    }
}