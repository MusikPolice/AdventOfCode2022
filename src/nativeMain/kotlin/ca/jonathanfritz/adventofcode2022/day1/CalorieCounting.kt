package ca.jonathanfritz.adventofcode2022.day1

/**
 * https://adventofcode.com/2022/day/1
 */
class CalorieCounting {

    fun count(lines: List<String>): Int {
        var max = Int.MIN_VALUE
        var sum = 0
        lines.forEach {
            if (it.isBlank()) {
                if (sum > max) {
                    max = sum
                }
                sum = 0
            } else {
                sum += it.toInt()
            }
        }
        return max
    }
}