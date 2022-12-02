package ca.jonathanfritz.adventofcode2022.day1

/**
 * Part 2 of https://adventofcode.com/2022/day/1
 */
class TopThree {

    fun sum(lines: List<String>): Int {
        val sums: MutableList<Int> = mutableListOf()
        var sum = 0
        lines.forEach {
            if (it.isBlank()) {
                sums.add(sum)
                sum = 0
            } else {
                sum += it.toInt()
            }
        }
        sums.sortDescending()
        return sums.take(3).sum()
    }
}