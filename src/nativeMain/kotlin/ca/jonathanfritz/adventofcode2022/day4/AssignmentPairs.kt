package ca.jonathanfritz.adventofcode2022.day4

class AssignmentPairs {

    // part 1
    fun countAssignmentOverlaps(assignmentPairs: List<String>): Long {
        return assignmentPairs.toIntRanges()
            .sumOf { ranges ->
                // check for overlaps
                if (ranges.first.fullyContains(ranges.second) || ranges.second.fullyContains(ranges.first)) {
                    1L
                } else {
                    0L
                }
            }
    }

    // part 2
    fun countAssignmentIntersections(assignmentPairs: List<String>): Long {
        return assignmentPairs.toIntRanges()
            .sumOf { ranges ->
                // check for intersections
                if (ranges.first.overlaps(ranges.second) || ranges.second.overlaps(ranges.first)) {
                    1L
                } else {
                    0L
                }
            }
    }

    private fun List<String>.toIntRanges(): List<Pair<IntRange, IntRange>> = this.map { pair ->
        // split the pair into two separate assignments
        val assignments = pair.split(",")
        assignments[0] to assignments[1]
    }.map { assignments ->
        // convert each assignment into an IntRange
        val a1 = assignments.first.split("-").map { it.toInt() }
        val a2 = assignments.second.split("-").map { it.toInt() }
        IntRange(a1[0], a1[1]) to IntRange(a2[0], a2[1])
    }

    private fun IntRange.fullyContains(other: IntRange): Boolean = this.first() <= other.first && this.last >= other.last

    private fun IntRange.overlaps(other: IntRange): Boolean = this.intersect(other).isNotEmpty()
}