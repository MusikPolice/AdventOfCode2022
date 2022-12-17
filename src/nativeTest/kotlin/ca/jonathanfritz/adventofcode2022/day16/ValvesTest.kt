package ca.jonathanfritz.adventofcode2022.day16

import ca.jonathanfritz.adventofcode2022.readTestResourcesFile
import kotlin.test.Test

internal class ValvesTest {

    private val valves = Valves()

    @Test
    fun parseInputTest() {
        val lines = readTestResourcesFile("day16/valves.txt")
        valves.part1(lines)
    }
}