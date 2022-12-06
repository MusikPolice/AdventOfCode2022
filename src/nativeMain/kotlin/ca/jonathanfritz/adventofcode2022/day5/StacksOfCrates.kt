package ca.jonathanfritz.adventofcode2022.day5

typealias Crate = Char

class StacksOfCrates {

    fun part1(lines: List<String>): String {
        // parse the initial state of each stack
        val initialState = lines.takeWhile { it.isNotBlank() }.toList()
        val stacks = parseInitialState(initialState)

        // parse the operations that will be executed against that state
        val instructions = lines.subList(initialState.size + 1, lines.size)
        val operations = parseOperations(instructions)

        // execute each operation
        operations.forEach { op ->
            (0 until op.count).forEach { _ ->
                // if source stack was empty, this will throw a null ref exception
                stacks[op.sink].push(stacks[op.source].pop()!!)
            }
        }

        // return the top letter on each stack
        return stacks.map { it.pop() ?: "" }.joinToString("")
    }

    fun part2(lines: List<String>): String {
        // parse the initial state of each stack
        val initialState = lines.takeWhile { it.isNotBlank() }.toList()
        val stacks = parseInitialState(initialState)

        // parse the operations that will be executed against that state
        val instructions = lines.subList(initialState.size + 1, lines.size)
        val operations = parseOperations(instructions)

        // execute each operation
        operations.forEach { op ->
            stacks[op.sink].push(stacks[op.source].pop(op.count))
        }

        // return the top letter on each stack
        return stacks.map { it.pop() ?: "" }.joinToString("")
    }

    private fun parseInitialState(lines: List<String>): List<Stack<Crate>> {
        // initialize the stacks
        val numStacks = lines.last().toCharArray().filter { it.isDigit() }.size
        val stacks: List<Stack<Crate>> = (0 until numStacks).map {
            Stack(lines.size - 1)
        }

        // parse the input into stacks
        lines.take(lines.size - 1) // omit the stack indices on the bottom line
            .reversed() // parse stacks bottom to top so that they come out in the correct order
            .map { line ->
                (0 until numStacks).forEach { stackNum ->
                    // we know the stack count and that each is 4 columns wide
                    val expectedIndex = (stackNum * 4) + 1
                    if (expectedIndex < line.length && line.toCharArray()[expectedIndex].isLetter()) {
                        // if there is a crate at this position, push it onto the stack
                        val crate: Crate = line.toCharArray()[expectedIndex]
                        stacks[stackNum].push(crate)
                    }
                }

            }

        return stacks
    }

    private fun parseOperations(lines: List<String>): List<Operation> {
        return lines.map { line ->
            // extract the three numbers from each line - if it starts with a digit, it must be a number!
            val numerals = line.split(" ")
                .filter { word -> word.trim().toCharArray()[0].isDigit() }
                .map { it.toInt() }

            // adjust for stack indices in the input starting at 1... filthy animals...
            Operation(numerals[0], numerals[1] - 1, numerals[2] - 1)
        }
    }

    class Stack<T>(size: Int) {
        private val internal: MutableList<T> = ArrayList(size)
        fun push(entity: T) = internal.add(0, entity)
        fun push(entities: List<T>) = internal.addAll(0, entities)
        fun pop(): T? = if (internal.isNotEmpty()) internal.removeAt(0) else null
        fun pop(count: Int): List<T> {
            if (internal.size >= count) {
                return (0 until count).map { internal.removeAt(0) }
            }
            return emptyList()
        }
        override fun toString(): String {
            return "t$internal"
        }
    }

    private data class Operation(
        val count: Int,
        val source: Int,
        val sink: Int
    )
}