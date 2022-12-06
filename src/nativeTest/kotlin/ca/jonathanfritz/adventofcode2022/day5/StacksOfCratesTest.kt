package ca.jonathanfritz.adventofcode2022.day5

import ca.jonathanfritz.adventofcode2022.readResourcesFile
import ca.jonathanfritz.adventofcode2022.readTestResourcesFile
import kotlin.test.Test
import kotlin.test.assertEquals

internal class StacksOfCratesTest {

    private val stacksOfCrates = StacksOfCrates()

    @Test
    fun stackTest() {
        val stack = StacksOfCrates.Stack<Int>(5)
        (0..5).map {
            stack.push(it)
        }
        (5 downTo 0).forEach {
            assertEquals(it, stack.pop())
        }
    }

    @Test
    fun stackTest2() {
        val stack = StacksOfCrates.Stack<Int>(5)
        (0..5).map {
            stack.push(it)
            println("Pushed $it. Stack is now $stack")
        }
        println()

        (5 downTo 0).forEach {_ ->
            println("Popped ${stack.pop()}. Stack is now $stack")
        }
    }

    @Test
    fun `Crate Stacking Test`() {
        val lines = readTestResourcesFile("day5/crates.txt")
        assertEquals("CMZ", stacksOfCrates.part1(lines))
    }

    @Test
    fun `Crate Stacking`() {
        val lines = readResourcesFile("day5/crates.txt")
        assertEquals("RLFNRTNFB", stacksOfCrates.part1(lines))
    }
}