package ca.jonathanfritz.adventofcode2022.day4

import ca.jonathanfritz.adventofcode2022.readResourcesFile
import ca.jonathanfritz.adventofcode2022.readTestResourcesFile
import kotlin.test.Test
import kotlin.test.assertEquals

internal class AssignmentPairsTest {

    private val ap = AssignmentPairs()

    @Test
    fun `Assignment Overlaps Test`() {
        val lines = readTestResourcesFile("day4/assignments.txt")
        assertEquals(2, ap.countAssignmentOverlaps(lines))
    }

    @Test
    fun `Assignment Overlaps`() {
        val lines = readResourcesFile("day4/assignments.txt")
        assertEquals(448, ap.countAssignmentOverlaps(lines))
    }

    @Test
    fun `Assignment Intersections Test`() {
        val lines = readTestResourcesFile("day4/assignments.txt")
        assertEquals(4, ap.countAssignmentIntersections(lines))
    }

    @Test
    fun `Assignment Intersections`() {
        val lines = readResourcesFile("day4/assignments.txt")
        assertEquals(794, ap.countAssignmentIntersections(lines))
    }
}