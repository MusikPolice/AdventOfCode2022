package ca.jonathanfritz.adventofcode2022.day12

import ca.jonathanfritz.adventofcode2022.readResourcesFile
import ca.jonathanfritz.adventofcode2022.readTestResourcesFile
import kotlin.test.Test
import kotlin.test.assertEquals


internal class PathfindingTest {

    private val pathfinding = Pathfinding()

    @Test
    fun `Shortest path test`() {
        val lines = readTestResourcesFile("day12/heightmap.txt")
        assertEquals(31, pathfinding.part1(lines))
    }

    @Test
    fun `Shortest path`() {
        val lines = readResourcesFile("day12/heightmap.txt")
        assertEquals(483, pathfinding.part1(lines)) // TODO: this is wrong
    }
}