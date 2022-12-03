package ca.jonathanfritz.adventofcode2022.day3

import ca.jonathanfritz.adventofcode2022.readResourcesFile
import ca.jonathanfritz.adventofcode2022.readTestResourcesFile
import kotlin.test.Test
import kotlin.test.assertEquals

internal class RucksackPrioritiesTest {

    private val rucksackPriorities = RucksackPriorities()

    @Test
    fun `character code test`() {
        // lowercase
        assertEquals(1, 'a'.code - 96)
        assertEquals(26, 'z'.code - 96)

        // uppercase
        assertEquals(27, 'A'.code - 38)
        assertEquals(52, 'Z'.code - 38)
    }

    @Test
    fun `Rucksack priorities score test`() {
        val lines = readTestResourcesFile("day3/rucksack.txt")
        assertEquals(157, rucksackPriorities.getDuplicateItemScores(lines))
    }

    @Test
    fun `Rucksack priorities score`() {
        val lines = readResourcesFile("day3/rucksack.txt")
        assertEquals(7795, rucksackPriorities.getDuplicateItemScores(lines))
    }

    @Test
    fun `Group Badge scores Test`() {
        val lines = readTestResourcesFile("day3/rucksack.txt")
        assertEquals(70, rucksackPriorities.getGroupBadgeScores(lines))
    }

    @Test
    fun `Group Badge scores`() {
        val lines = readResourcesFile("day3/rucksack.txt")
        assertEquals(2703, rucksackPriorities.getGroupBadgeScores(lines))
    }
}