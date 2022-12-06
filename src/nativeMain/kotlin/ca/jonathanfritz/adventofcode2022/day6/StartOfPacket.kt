package ca.jonathanfritz.adventofcode2022.day6

class StartOfPacket {

    fun part1 (datastream: String) = findUniqueSubstringOffset(datastream, 4)

    fun part2 (datastream: String) = findUniqueSubstringOffset(datastream, 14)

    private fun findUniqueSubstringOffset(datastream: String, substringLength: Int): Int {
        (0..datastream.length - substringLength).forEach { offset ->
            val substring = datastream.substring(offset, offset + substringLength)
            if (substring.toCharArray().none { char -> substring.toCharArray().count { it == char } > 1 })
                return offset + substringLength
        }
        return -1
    }
}