package ca.jonathanfritz.adventofcode2022.day9

import kotlin.math.max

class PushingRope {

    fun part1(lines: List<String>): Int {
        val tails: MutableSet<Point> = mutableSetOf()

        var rope = Rope()
        lines.forEach {
            val direction = it.split(" ")[0]
            val steps = it.split(" ")[1].toInt()

            // println("== Instruction:$it ==")
            for (i in 0 until steps) {
                rope = rope.pull(direction)
                tails.add(rope.tail)
            }
            // println(rope.toString())
        }

        return tails.distinct().count()
    }

    data class Rope(
        val head: Point = Point(),
        val tail: Point = Point()
    ) {
        fun pull(direction: String): Rope {
            val newHead = when (direction) {
                "U" -> head.moveUp()
                "L" -> head.moveLeft()
                "D" -> head.moveDown()
                "R" -> head.moveRight()
                else -> throw IllegalArgumentException("Direction $direction is not supported")
            }
            val newTail = tail.follow(newHead)
            return Rope(newHead, newTail)
        }

        override fun toString(): String {
            val width = max(6, max(head.x, tail.x))
            val height = max(6, max(head.y, tail.y))
            val sb = StringBuilder()
            (height - 1 downTo 0).forEach { y ->
                (0 until width).forEach { x ->
                    sb.append(
                        if (x == head.x && y == head.y) {
                            "H"
                        } else if (x == tail.x && y == tail.y) {
                            "T"
                        } else if (x == 0 && y == 0) {
                            "s"
                        } else {
                            "."
                        }
                    )
                }
                sb.appendLine()
            }
            return sb.toString()
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

            // if yDist == 0, move is strictly left or right
            if (yDist == 0) {
                if (xDist > 1) return this.moveRight()
                else if (xDist < -1) return this.moveLeft()
            }

            // if xDist == 0, move is strictly up or down
            if (xDist == 0) {
                if (yDist > 1) return this.moveUp()
                else if (yDist < -1) return this.moveDown()
            }

            // if xDist == 1 || xDist == -1, move is diagonal
            if (xDist == 1) {
                if (yDist > 1) return this.moveUp().moveRight()
                if (yDist < -1) return this.moveDown().moveRight()
            } else if (xDist == -1) {
                if (yDist > 1) return this.moveUp().moveLeft()
                if (yDist < -1) return this.moveDown().moveLeft()
            }

            // if yDist == 1 || yDist == -1, move is diagonal
            if (yDist == 1) {
                if (xDist > 1) return this.moveRight().moveUp()
                if (xDist < -1) return this.moveLeft().moveUp()
            } else if (yDist == -1) {
                if (xDist > 1) return this.moveRight().moveDown()
                if (xDist < -1) return this.moveLeft().moveDown()
            }

            return this
        }
    }
}