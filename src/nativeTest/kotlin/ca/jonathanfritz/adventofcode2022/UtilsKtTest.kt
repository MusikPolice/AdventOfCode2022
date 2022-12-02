package ca.jonathanfritz.adventofcode2022

import kotlin.test.Test
import kotlin.test.assertEquals

internal class UtilsKtTest {

    @Test
    fun `read file contents test`() {
        val lines = readTestResourcesFile("test.txt")
        assertEquals(2, lines.size)
        assertEquals("Hello", lines[0])
        assertEquals("World", lines[1])
    }
}