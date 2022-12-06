package ca.jonathanfritz.adventofcode2022.day6

class StartOfPacket {

    fun part1 (datastream: String): Int {
        (0 .. datastream.length - 4).forEach { offset ->
            val substring = datastream.substring(offset, offset+4)
            if (substring.toCharArray().none { char -> substring.toCharArray().count { it == char } > 1 })
            return offset+4
        }
        return -1
    }
}