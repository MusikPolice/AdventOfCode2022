package ca.jonathanfritz.adventofcode2022.day8

class TreeVisibility {

    fun part1(lines: List<String>): Int {
        // input is a grid, determine the boundaries
        val width = lines[0].length
        val height = lines.size

        // all trees on the outside are visible by definition
        var numVisibleTrees = (width * 2) + ((height - 2) * 2)

        // indexed (y,x)
        val heights = lines.map { line -> line.toCharArray().map { char -> char.digitToInt() }.toList() }

        // check the visibility of each interior tree
        for (x in (1 until width - 1)) {
            for (y in (1 until height - 1)) {
                if (isVisible(heights, width, height, x, y)) {
                    numVisibleTrees++
                }
            }
        }

        return numVisibleTrees
    }

    private fun isVisible(heights: List<List<Int>>, width: Int, height: Int, x: Int, y: Int): Boolean {
        // look up - decrease y
        if ((y downTo 0).filter { it != y }.map { heights[it][x] }.all { it < heights[y][x] }) {
            return true
        }

        // look right - increase x
        if ((x until width).filter { it != x }.map { heights[y][it] }.all { it < heights[y][x] }) {
            return true
        }

        // look down - increase y
        if ((y until height).filter { it != y }.map { heights[it][x] }.all { it < heights[y][x] }) {
            return true
        }

        // look left - decrease x
        if ((x downTo 0).filter { it != x }.map { heights[y][it] }.all { it < heights[y][x] }) {
            return true
        }

        return false
    }
}