package ca.jonathanfritz.adventofcode2022.day1

/**
 * Part 2 of https://adventofcode.com/2022/day/1
 */
class TopThree {

    fun sum(lines: List<String>): Int {
        var top3: MutableList<Int> = mutableListOf()
        var sum = 0
        lines.forEach {
            if (it.isBlank()) {
                top3.add(sum)
                top3.sortDescending()
                top3 = top3.take(3).toMutableList()
                sum = 0
            } else {
                sum += it.toInt()
            }
        }

        return top3.sum()
    }
}