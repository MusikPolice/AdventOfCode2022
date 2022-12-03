package ca.jonathanfritz.adventofcode2022.day2

import ca.jonathanfritz.adventofcode2022.readResourcesFile
import ca.jonathanfritz.adventofcode2022.readTestResourcesFile
import kotlin.test.Test
import kotlin.test.assertEquals

internal class RockPaperScissorsTest {

    private val rps = RockPaperScissors()

    @Test
    fun `String to Shape Test`() {
        assertEquals(RockPaperScissors.Shape.ROCK, "X".toShape())
        assertEquals(RockPaperScissors.Shape.ROCK, "A".toShape())

        assertEquals(RockPaperScissors.Shape.PAPER, "Y".toShape())
        assertEquals(RockPaperScissors.Shape.PAPER, "B".toShape())

        assertEquals(RockPaperScissors.Shape.SCISSORS, "Z".toShape())
        assertEquals(RockPaperScissors.Shape.SCISSORS, "C".toShape())
    }

    @Test
    fun `Line Split Test`() {
        val chars = "A Y".split(" ")
        assertEquals(RockPaperScissors.Shape.ROCK, chars[0].toShape())
        assertEquals(RockPaperScissors.Shape.PAPER, chars[1].toShape())
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