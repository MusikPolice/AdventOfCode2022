package ca.jonathanfritz.adventofcode2022.day9

import kotlin.math.max

class PushingRope {

    fun part1(lines: List<String>): Int {
        return pullRope(lines, 2)
    }

    fun part2(lines: List<String>): Int {
        return pullRope(lines, 10)
    }

    private fun pullRope(lines: List<String>, numKnots: Int): Int {
        val tails: MutableSet<Point> = mutableSetOf()

        var rope = Rope((0 until numKnots).map { Point() })
        lines.forEach {
            val direction = it.split(" ")[0]
            val steps = it.split(" ")[1].toInt()

            for (i in 0 until steps) {
                rope = rope.pull(direction)
                tails.add(rope.knots.last())
            }
        }

        return tails.distinct().count()
    }


    data class Rope(
        val knots: List<Point>
    ) {
        fun pull(direction: String): Rope {
            // move the head (i.e. the zeroth knot)
            val head = when (direction) {
                "U" -> knots[0].moveUp()
                "L" -> knots[0].moveLeft()
                "D" -> knots[0].moveDown()
                "R" -> knots[0].moveRight()
                else -> throw IllegalArgumentException("Direction $direction is not supported")
            }

            // propagate the movement through the remaining knots, with each following the previous
            val newKnots: MutableList<Point> = mutableListOf(head)
            for (i in (1 until knots.size)) {
                newKnots.add(knots[i].follow(newKnots[i-1]))
            }

            return Rope(newKnots)
        }
    }

    data class Point(
        val x: Int = 0,
        val y: Int = 0
    ) {
        fun moveUp(): Point = this.copy(y = y + 1)
        fun moveLeft(): Point = this.copy(x = x - 1)
        fun moveDown(): Point = this.copy(y = y - 1)
        fun moveRight(): Point = this.copy(x = x + 1)

        fun follow(other: Point): Point {
            val xDist = other.x - x
            val yDist = other.y - y

            // left and right moves
            if (yDist == 0) {
                if (xDist > 1) return this.moveRight()
                else if (xDist < -1) return this.moveLeft()
            }

            // up and down moves
            if (xDist == 0) {
                if (yDist > 1) return this.moveUp()
                else if (yDist < -1) return this.moveDown()
            }

            // left and right diagonal moves
            if (xDist >= 1) {
                if (yDist > 1) return this.moveUp().moveRight()
                if (yDist < -1) return this.moveDown().moveRight()
            } else if (xDist <= -1) {
                if (yDist > 1) return this.moveUp().moveLeft()
                if (yDist < -1) return this.moveDown().moveLeft()
            }

            // up and down diagonal moves
            if (yDist >= 1) {
                if (xDist > 1) return this.moveRight().moveUp()
                if (xDist < -1) return this.moveLeft().moveUp()
            } else if (yDist <= -1) {
                if (xDist > 1) return this.moveRight().moveDown()
                if (xDist < -1) return this.moveLeft().moveDown()
            }

            return this
        }
    }
}