package ca.jonathanfritz.adventofcode2022.day3

class RucksackPriorities {

    fun getDuplicateItemScores(lines: List<String>) = lines.map {
        // split the contents of each rucksack into two compartments
        it.substring(0, it.length / 2) to it.substring(it.length / 2)
    }.map {
        // find common items between the two compartments
        it.second.toCharArray().filter { c -> it.first.toCharArray().contains(c) }.distinct().first()
    }.sumOf {
        // score common items. Lowercase chars score 1-26, uppercase score 27-52
        if (it.isLowerCase()) {
            it.code - 96
        } else {
            it.code - 38
        }
    }
}