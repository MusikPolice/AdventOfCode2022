package ca.jonathanfritz.adventofcode2022.day9

import ca.jonathanfritz.adventofcode2022.readResourcesFile
import ca.jonathanfritz.adventofcode2022.readTestResourcesFile
import kotlin.test.Test
import kotlin.test.assertEquals

internal class PushingRopeTest {

    private val pushingRope = PushingRope()

    @Test
    fun `Pushing rope test`() {
        val lines = readTestResourcesFile("day9/rope.txt")
        assertEquals(13, pushingRope.part1(lines))
    }

    @Test
    fun `Pushing rope`() {
        val lines = readResourcesFile("day9/rope.txt")
        assertEquals(6023, pushingRope.part1(lines))
    }
}