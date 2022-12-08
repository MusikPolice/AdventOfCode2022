package ca.jonathanfritz.adventofcode2022.day7

import ca.jonathanfritz.adventofcode2022.readResourcesFile
import ca.jonathanfritz.adventofcode2022.readTestResourcesFile
import kotlin.test.Test
import kotlin.test.assertEquals

internal class FilesystemTest {

    private val fs = Filesystem()

    @Test
    fun `Compute sum of directory sizes test`() {
        val lines = readTestResourcesFile("day7/terminal.txt")
        assertEquals(95437, fs.part1(lines))
    }

    @Test
    fun `Compute sum of directory sizes`() {
        val lines = readResourcesFile("day7/terminal.txt")
        assertEquals(1391690, fs.part1(lines))
    }

    @Test
    fun `Delete directories test`() {
        val lines = readTestResourcesFile("day7/terminal.txt")
        assertEquals(24933642, fs.part2(lines))
    }

    @Test
    fun `Delete directories`() {
        val lines = readResourcesFile("day7/terminal.txt")
        assertEquals(5469168, fs.part2(lines))
    }
}