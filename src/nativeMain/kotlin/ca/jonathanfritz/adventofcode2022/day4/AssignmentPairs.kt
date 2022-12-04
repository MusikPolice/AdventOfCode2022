package ca.jonathanfritz.adventofcode2022.day4

class AssignmentPairs {

    fun countAssignmentOverlaps(assignmentPairs: List<String>): Long {
        return assignmentPairs.map {pair ->
            // split the pair into two separate assignments
            val assignments = pair.split(",")
            assignments[0] to assignments[1]
        }.map {assignments ->
            // convert each assignment into an IntRange
            val a1 = assignments.first.split("-").map { it.toInt() }
            val a2 = assignments.second.split("-").map { it.toInt() }
            IntRange(a1[0], a1[1])to IntRange(a2[0], a2[1])
        }.sumOf { ranges ->
            // check for overlaps
            if (ranges.first.containsInclusive(ranges.second) || ranges.second.containsInclusive(ranges.first)) {
                1L
            } else {
                0L
            }
        }
    }

    // returns true if the receiver fully contains other
    private fun IntRange.containsInclusive(other: IntRange) = this.first() <= other.first && this.last >= other.last
}