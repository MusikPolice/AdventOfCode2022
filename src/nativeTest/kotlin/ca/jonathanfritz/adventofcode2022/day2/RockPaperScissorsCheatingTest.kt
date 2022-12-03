package ca.jonathanfritz.adventofcode2022.day2

import ca.jonathanfritz.adventofcode2022.readResourcesFile
import ca.jonathanfritz.adventofcode2022.readTestResourcesFile
import kotlin.test.Test
import kotlin.test.assertEquals


internal class RockPaperScissorsCheatingTest {

    private val rps = RockPaperScissorsCheating()

    @Test
    fun `Rock paper scissors cheating test`() {
        val lines = readTestResourcesFile("day2/tournament.txt")
        assertEquals(12, rps.tournamentScore(lines))
    }

    @Test
    fun `Rock paper scissors cheating`() {
        val lines = readResourcesFile("day2/tournament.txt")
        assertEquals(15702, rps.tournamentScore(lines))
    }
}