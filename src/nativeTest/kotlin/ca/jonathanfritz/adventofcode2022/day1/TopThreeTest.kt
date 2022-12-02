package ca.jonathanfritz.adventofcode2022.day1

import ca.jonathanfritz.adventofcode2022.readResourcesFile
import ca.jonathanfritz.adventofcode2022.readTestResourcesFile
import kotlin.test.Test
import kotlin.test.assertEquals

internal class TopThreeTest {

    private val topThree = TopThree()

    @Test
    fun `top three test`() {
        val lines = readTestResourcesFile("day1/CalorieCounting.txt")
        assertEquals(45000, topThree.sum(lines))
    }

    @Test
    fun `top three`() {
        val lines = readResourcesFile("day1/CalorieCounting.txt")
        println(topThree.sum(lines))
    }

}