package ca.jonathanfritz.adventofcode2022.day14

import ca.jonathanfritz.adventofcode2022.readResourcesFile
import ca.jonathanfritz.adventofcode2022.readTestResourcesFile
import kotlin.test.Test
import kotlin.test.assertEquals

internal class FallingSandTest {

    private val fallingSand = FallingSand()

    @Test
    fun `Falling Sand Test`() {
        val lines = readTestResourcesFile("day14/rocks.txt")
        assertEquals(24, fallingSand.part1(lines))
    }

    @Test
    fun `Falling Sand`() {
        val lines = readResourcesFile("day14/rocks.txt")
        assertEquals(715, fallingSand.part1(lines))
    }

    @Test
    fun `Falling Sand with Floor Test`() {
        val lines = readTestResourcesFile("day14/rocks.txt")
        assertEquals(93, fallingSand.part2(lines))
    }

    @Test
    fun `Falling Sand with Floor`() {
        val lines = readResourcesFile("day14/rocks.txt")
        assertEquals(25248, fallingSand.part2(lines))
    }
}