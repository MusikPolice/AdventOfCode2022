package ca.jonathanfritz.adventofcode2022.day10

import ca.jonathanfritz.adventofcode2022.readResourcesFile
import ca.jonathanfritz.adventofcode2022.readTestResourcesFile
import kotlin.test.Test
import kotlin.test.assertEquals

internal class CrtTest {

    private val crt = Crt()
    
    @Test
    fun signalStrengthTest() {
        val lines = readTestResourcesFile("day10/instructions.txt")
        assertEquals(13140, crt.part1(lines))
    }

    @Test
    fun signalStrength() {
        val lines = readResourcesFile("day10/instructions.txt")
        assertEquals(14160, crt.part1(lines))
    }
}