package ca.jonathanfritz.adventofcode2022.day15

import ca.jonathanfritz.adventofcode2022.readResourcesFile
import ca.jonathanfritz.adventofcode2022.readTestResourcesFile
import kotlin.test.Test
import kotlin.test.assertEquals

internal class SensorsTest {

    private val sensors = Sensors()

    @Test
    fun manhattanDistanceTest() {
        val p1 = Sensors.Point(8, 7)
        val p2 = Sensors.Point(2, 10)
        assertEquals(0, p1.manhattanDistance(p1))
        assertEquals(9, p1.manhattanDistance(p2))
        assertEquals(9, p2.manhattanDistance(p1))
    }

    @Test
    fun `Beacon distance test`() {
        val lines = readTestResourcesFile("day15/sensors.txt")
        assertEquals(26, sensors.part1(lines))
    }

    @Test
    fun `Beacon distance`() {
        val lines = readResourcesFile("day15/sensors.txt")
        assertEquals(26, sensors.part1(lines, y=2000000))
    }
}