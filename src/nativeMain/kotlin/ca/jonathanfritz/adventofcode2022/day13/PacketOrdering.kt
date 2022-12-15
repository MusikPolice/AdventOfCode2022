package ca.jonathanfritz.adventofcode2022.day13

class PacketOrdering {

    fun part1(lines: List<String>): Int {
        // parse input
        val packets: List<Pair<ListNode, ListNode>> = parseInput(lines)
        packets.forEach {
            println(it.first)
            println(it.second)
            println()
        }

        return 0
    }

    private fun parseInput(lines: List<String>): List<Pair<ListNode, ListNode>> {
        val packets: MutableList<Pair<ListNode, ListNode>> = mutableListOf()
        var first: ListNode? = null
        var second: ListNode? = null
        for (offset in lines.indices step 3) {
            if ((offset + 1) % 1 == 0) {
                first = lines[offset].toListNode()
            } else if ((offset + 1) % 2 == 0) {
                second = lines[offset].toListNode()
            } else {
                // break between lines, reset state to read next pair of packets
                packets.add(first!! to second!!)
                first = null
                second = null
            }
        }

        return packets.toList()
    }

    private fun String.toListNode(): ListNode {
        val stack: Stack<String> = Stack(this.replace(",", "").length)
        val chunks = this.split(",")
        for (chunk in chunks) {
            if (chunk.isNumeric()) {
                // integer list element
                stack.push(chunk)
            }

            // push any start of list indicators
            var temp = chunk
            while (temp.startsWith("[")) {
                temp = temp.removePrefix("[")
                stack.push("[")
            }

            // remaining temp must start with either an integer or an end of list indicator
            val endOfListIndex = temp.indexOf("]")
            temp = if (endOfListIndex == -1) {
                // remainder is a number
                if (!temp.isNumeric()) {
                    throw IllegalStateException("Expected $temp to be a number")
                }
                stack.push(temp)
                ""
            } else {
                // remainder is a number followed by one or more end of list indicators
                val number = temp.substring(0, endOfListIndex - 1)
                if (!number.isNumeric()) {
                    throw IllegalStateException("Expected $number to be a number")
                }
                stack.push(number)
                temp.removePrefix(number)
            }

            // remaining temp must be empty or entirely end of list indicators
            for (char in temp.toCharArray()) {
                if (char == ']') {
                    // TODO: pop until we find start of list indicator, assemble results into a NodeList
                } else {
                    throw IllegalStateException("Expected $char to be end of list indicator")
                }
            }
        }

        return ListNode(emptyList())
    }

    private fun String.isNumeric(): Boolean = this.toIntOrNull() != null

    abstract class Node()
    class ListNode(val value: List<Node>) : Node()
    class IntNode(val value: Int) : Node()

    class Stack<T>(size: Int) {
        private val internal: MutableList<T> = ArrayList(size)
        fun push(entity: T) = internal.add(0, entity)

        // fun push(entities: List<T>) = internal.addAll(0, entities)
        fun pop(): T? = if (internal.isNotEmpty()) internal.removeAt(0) else null

        /*fun pop(count: Int): List<T> {
            if (internal.size >= count) {
                return (0 until count).map { internal.removeAt(0) }
            }
            return emptyList()
        }*/
        override fun toString(): String {
            return "t$internal"
        }
    }
}


