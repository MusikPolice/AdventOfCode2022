package ca.jonathanfritz.adventofcode2022.day1

import ca.jonathanfritz.adventofcode2022.readResourcesFile
import ca.jonathanfritz.adventofcode2022.readTestResourcesFile
import kotlin.test.Test
import kotlin.test.assertEquals

internal class CalorieCountingTest {

    private val calorieCounting = CalorieCounting()

    @Test
    fun `count calories test`() {
        val lines = readTestResourcesFile("day1/CalorieCounting.txt")
        assertEquals(24000, calorieCounting.count(lines))
    }

    @Test
    fun `count calories`() {
        val lines = readResourcesFile("day1/CalorieCounting.txt")
        println(calorieCounting.count(lines))
    }
}