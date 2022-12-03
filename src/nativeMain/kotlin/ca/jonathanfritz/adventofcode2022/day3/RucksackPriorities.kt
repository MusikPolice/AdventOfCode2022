package ca.jonathanfritz.adventofcode2022.day3

class RucksackPriorities {

    // part 1
    fun getDuplicateItemScores(lines: List<String>) = lines.map {
        // split the contents of each rucksack into two compartments
        it.substring(0, it.length / 2) to it.substring(it.length / 2)
    }.map {
        // find common items between the two compartments
        it.second.toCharArray().filter { c -> it.first.toCharArray().contains(c) }.distinct().first()
    }.score()

    // part 2
    fun getGroupBadgeScores(lines: List<String>): Int {
        return (lines.indices step 3).map {
            // split rucksacks into groups of three elves
            listOf(lines[it], lines[it+1], lines[it+2])
        }.map {
            // find the common item in the group's rucksacks
            it[0].first { c ->
                it[1].contains(c) && it[2].contains(c)
            }
        }.score()
    }

    private fun List<Char>.score() : Int = this.sumOf {
        // score common items. Lowercase chars score 1-26, uppercase score 27-52
        if (it.isLowerCase()) {
            it.code - 96
        } else {
            it.code - 38
        }
    }
}