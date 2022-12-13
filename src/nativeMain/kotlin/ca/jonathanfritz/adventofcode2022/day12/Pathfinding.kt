package ca.jonathanfritz.adventofcode2022.day12

import kotlin.math.pow
import kotlin.math.sqrt

class Pathfinding {

    fun part1(lines: List<String>): Int {
        // convert alphabetic heightmap to integer heights so that we can do math
        val heightMap = generateHeightMap(lines)
        (lines.indices).forEach { i ->
            println(heightMap.heights[i])
        }

        // get start position and target end position
        // [0,0] is in the top-left corner of heightmap
        val (start, end) = getStartAndEndPosition(lines)
        println("Start: $start, End: $end")

        // start at the start and figure out how to get to the end
        val path = aStar(start, end, heightMap)
        println(path)

        // we don't include start (the first point in the path) b/c we're counting num steps taken
        return path.size - 1
    }

    private fun aStar(start: Point, end: Point, heightMap: HeightMap): MutableList<Point> {
        // open nodes to expand on in search of a path to the end
        val open = mutableSetOf(start)

        // tracks the previous point in the path from start to key
        val parent: MutableMap<Point, Point> = mutableMapOf()

        // score of cheapest path from start to key
        val gScore: MutableMap<Point, Float> = mutableMapOf(start to 0f)

        // best guess of how cheap a path from start to finish could be if it goes through the key
        val fScore: MutableMap<Point, Float> = mutableMapOf(start to 0f)

        while (open.isNotEmpty()) {
            // get the open node with the smallest fScore
            val current = open.minByOrNull { fScore[it] ?: Float.MAX_VALUE }!!
            if (current == end) {
                return reconstructPath(parent, current)
            }

            open.remove(current)
            heightMap.legalMoves(current).forEach { neighbour ->
                val tentativeGScore = (gScore[current] ?: Float.MAX_VALUE) + 1
                if (tentativeGScore < (gScore[neighbour] ?: Float.MAX_VALUE)) {
                    // this path to neighbour is better than any previous one
                    parent[neighbour] = current
                    gScore[neighbour] = tentativeGScore + 1
                    fScore[neighbour] = tentativeGScore + neighbour.distanceTo(end)
                    if (!open.contains(neighbour)) {
                        open.add(neighbour)
                    }
                }
            }
        }

        throw IllegalStateException("No path found")
    }

    private fun reconstructPath(parent: Map<Point, Point>, end: Point): MutableList<Point> {
        val path = mutableListOf(end)
        var current = end
        while (current in parent.keys) {
            current = parent[current]!!
            path.add(0, current)
        }
        return path
    }

    // maps the letter based heights (a-z) to int based heights (1-26)
    private fun generateHeightMap(lines: List<String>): HeightMap {
        return HeightMap(lines.mapIndexed { y, line ->
            y to line.toCharArray().map { char ->
                when (char) {
                    'S' -> 0
                    'E' -> 26
                    else -> char.code - 97
                }
            }
        }.toMap())
    }

    private fun getStartAndEndPosition(lines: List<String>): Pair<Point, Point> {
        var start: Point? = null
        var end: Point? = null
        for (y in lines.indices) {
            val chars = lines[y].toCharArray()
            for (x in (0 until lines[0].length)) {
                if (chars[x] == 'S') {
                    start = Point(x, y)
                } else if (chars[x] == 'E') {
                    end = Point(x, y)
                }
                if (start != null && end != null) {
                    return start to end
                }
            }
        }
        throw IllegalArgumentException("Failed to find S or E")
    }

    data class HeightMap(
        val heights: Map<Int, List<Int>>
    ) {
        val width: Int = heights[heights.keys.first()]?.size
            ?: throw IllegalArgumentException("Heightmap has zero height")
        val height: Int = heights.keys.size
    }

    data class Point(
        val x: Int,
        val y: Int
    ) {
        fun moveUp(): Point = this.copy(y = y + 1)
        fun moveLeft(): Point = this.copy(x = x - 1)
        fun moveDown(): Point = this.copy(y = y - 1)
        fun moveRight(): Point = this.copy(x = x + 1)
        fun isInBounds(width: Int, height: Int): Boolean = (x >= 0) && (x < width) && (y >= 0) && (y < height)
        fun distanceTo(other: Point): Float = sqrt( (other.x - this.x).toFloat().pow(2) + (other.y - this.y).toFloat().pow(2))
    }
}

private fun Pathfinding.HeightMap.legalMoves(current: Pathfinding.Point): List<Pathfinding.Point> {
    return listOf(
        // we can move in any of the four cardinal directions
        current.moveUp(),
        current.moveRight(),
        current.moveDown(),
        current.moveLeft()
    ).filter { target ->
        // remain in bounds after the move
        if (!target.isInBounds(this.width, this.height)) return@filter false

        val currentHeight = (this.heights[current.y]?.get(current.x))
            ?: throw IndexOutOfBoundsException("$current is out of bounds")
        val targetHeight = this.heights[target.y]?.get(target.x)
            ?: throw IndexOutOfBoundsException("$target is out of bounds")

        // and the height of target position is <= current elevation + 1
        targetHeight <= (currentHeight + 1)
    }
}
