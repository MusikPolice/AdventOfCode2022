package ca.jonathanfritz.adventofcode2022.day15

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Sensors {

    fun part1(lines: List<String>, y: Int = 10): Int {
        val sensors = parseInput(lines)
        val beacons = sensors.map { it.nearestBeacon }.distinct().map { Beacon(it) }
        println("Sensors: ${sensors.map { it.position }}")
        println("Beacons: ${beacons.map {it.position}}")

        val minX = beacons.minOf { it.position.x } // min(sensors.minOf { it.position.x }, beacons.minOf { it.position.x })
        val minY = beacons.minOf { it.position.y } // min(sensors.minOf { it.position.y }, beacons.minOf { it.position.y })
        val maxX = beacons.maxOf { it.position.x } // max(sensors.maxOf { it.position.x }, beacons.maxOf { it.position.x })
        val maxY = beacons.maxOf { it.position.y } // max(sensors.maxOf { it.position.y }, beacons.maxOf { it.position.y })
        println("Boundaries are ($minX, $minY) to ($maxX, $maxY)")

        // scan from minX to maxX at specified y height
        var count = 0
        for (x in (minX .. maxX)) {
            val point = Point(x, y)

            // we're looking for positions that cannot contain a beacon, so omit positions that do
            if (beacons.map { it.position }.contains(point)) continue

            // check if this position is in range of any sensor that we know about
            if (sensors.firstOrNull { it.isInRange(point) } != null) {
                count++
            }
        }
        println("There are $count positions where a beacon cannot be present")

        // solution 4341076 is too low - there are more possible beacon positions than we are considering
        return count
    }

    private fun parseInput(lines: List<String>): List<Sensor> {
        return lines.map { line ->
            val ints = line.replace("Sensor at x=", "")
                .replace(" y=", "")
                .replace(": closest beacon is at x=", ",")
                .split(",")
                .map { it.trim().toInt() }
            Sensor(Point(ints[0], ints[1]), Point(ints[2], ints[3]))
        }
    }

    data class Sensor(
        val position: Point,
        val nearestBeacon: Point
    ) {
        private val minRange = position.manhattanDistance(nearestBeacon)
        fun isInRange(other: Point): Boolean = position.manhattanDistance(other) < minRange
    }

    data class Beacon(
        val position: Point
    )

    data class Point(
        val x: Int,
        val y: Int
    ) {
        fun manhattanDistance(other: Point): Int {
            return abs(this.x - other.x) + abs(this.y - other.y)
        }

        override fun toString(): String {
            return "($x, $y)"
        }
    }
}