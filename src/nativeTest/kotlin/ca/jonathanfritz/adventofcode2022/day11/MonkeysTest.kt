package ca.jonathanfritz.adventofcode2022.day11

import ca.jonathanfritz.adventofcode2022.readResourcesFile
import ca.jonathanfritz.adventofcode2022.readTestResourcesFile
import kotlin.test.Test
import kotlin.test.assertEquals

internal class MonkeysTest {

    private val monkeys = Monkeys()

    @Test
    fun `Monkey business test`() {
        val lines = readTestResourcesFile("day11/monkeybusiness.txt")
        assertEquals(10605, monkeys.part1(lines))
    }

    @Test
    fun `Monkey business`() {
        val lines = readResourcesFile("day11/monkeybusiness.txt")
        assertEquals(55944, monkeys.part1(lines))
    }
}