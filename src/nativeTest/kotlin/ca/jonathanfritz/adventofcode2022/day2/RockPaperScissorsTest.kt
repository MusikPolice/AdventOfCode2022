package ca.jonathanfritz.adventofcode2022.day2

import ca.jonathanfritz.adventofcode2022.readResourcesFile
import ca.jonathanfritz.adventofcode2022.readTestResourcesFile
import kotlin.test.Test
import kotlin.test.assertEquals

internal class RockPaperScissorsTest {

    private val rps = RockPaperScissors()

    @Test
    fun `String to Shape Test`() {
        assertEquals(RockPaperScissors.Shape.ROCK, RockPaperScissors.Shape.fromString("X"))
        assertEquals(RockPaperScissors.Shape.ROCK, RockPaperScissors.Shape.fromString("A"))

        assertEquals(RockPaperScissors.Shape.PAPER, RockPaperScissors.Shape.fromString("Y"))
        assertEquals(RockPaperScissors.Shape.PAPER, RockPaperScissors.Shape.fromString("B"))

        assertEquals(RockPaperScissors.Shape.SCISSORS, RockPaperScissors.Shape.fromString("Z"))
        assertEquals(RockPaperScissors.Shape.SCISSORS, RockPaperScissors.Shape.fromString("C"))
    }

    @Test
    fun `Line Split Test`() {
        val chars = "A Y".split(" ")
        assertEquals(RockPaperScissors.Shape.ROCK, RockPaperScissors.Shape.fromString(chars[0]))
        assertEquals(RockPaperScissors.Shape.PAPER, RockPaperScissors.Shape.fromString(chars[1]))
    }

    @Test
    fun `Rock paper scissors tournament test`() {
        val lines = readTestResourcesFile("day2/tournament.txt")
        assertEquals(15, rps.tournamentScore(lines))
    }

    @Test
    fun `Rock paper scissors tournament`() {
        val lines = readResourcesFile("day2/tournament.txt")
        assertEquals(15523, rps.tournamentScore(lines))
    }
}