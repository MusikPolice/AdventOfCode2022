package ca.jonathanfritz.adventofcode2022.day8

import ca.jonathanfritz.adventofcode2022.readResourcesFile
import ca.jonathanfritz.adventofcode2022.readTestResourcesFile
import kotlin.test.Test
import kotlin.test.assertEquals


internal class TreeVisibilityTest {

    private val treeVisibility = TreeVisibility()

    @Test
    fun `Tree visibility test`() {
        val lines = readTestResourcesFile("day8/TreeVisibility.txt")
        assertEquals(21, treeVisibility.part1(lines))
    }

    @Test
    fun `Tree visibility`() {
        val lines = readResourcesFile("day8/TreeVisibility.txt")
        assertEquals(1845, treeVisibility.part1(lines))
    }
}