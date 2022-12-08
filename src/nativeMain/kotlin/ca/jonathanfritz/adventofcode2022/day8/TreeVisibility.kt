package ca.jonathanfritz.adventofcode2022.day8

class TreeVisibility {

    fun part1(lines: List<String>): Int {
        // input is a grid, determine the boundaries
        val width = lines[0].length
        val height = lines.size

        // indexed (y,x)
        val heights = lines.map { line -> line.toCharArray().map { char -> char.digitToInt() }.toList() }

        // check the visibility of each interior tree
        // all trees on the outside are visible by definition
        var numVisibleTrees = (width * 2) + ((height - 2) * 2)
        for (x in (1 until width - 1)) {
            for (y in (1 until height - 1)) {
                if (isVisible(heights, width, height, x, y)) {
                    numVisibleTrees++
                }
            }
        }

        return numVisibleTrees
    }

    fun part2(lines: List<String>): Int {
        // input is a grid, determine the boundaries
        val width = lines[0].length
        val height = lines.size

        // indexed (y,x)
        val heights = lines.map { line -> line.toCharArray().map { char -> char.digitToInt() }.toList() }

        // get the scenic score for each tree
        var maxScenicScore = 0
        for (x in (0 until width)) {
            for (y in (0 until height)) {
                val scenicScore = scenicScore(heights, width, height, x, y)
                if (scenicScore > maxScenicScore) {
                    maxScenicScore = scenicScore
                }
            }
        }

        return maxScenicScore
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

    private fun scenicScore(heights: List<List<Int>>, maxX: Int, maxY: Int, x: Int, y: Int): Int {
        // look up - decrease y
        var up = 0
        for (i in ((y downTo 0).filter { it != y })) {
            up++
            if (heights[i][x] >= heights[y][x]) break
        }

        // look right - increase x
        var right = 0
        for (i in (x until maxX).filter { it != x }) {
            right++
            if (heights[y][i] >= heights[y][x]) break
        }

        // look down - increase y
        var down = 0
        for (i in (y until maxY).filter { it != y }) {
            down++
            if (heights[i][x] >= heights[y][x]) break
        }

        // look left - decrease x
        var left = 0
        for (i in (x downTo 0).filter { it != x }) {
            left++
            if (heights[y][i] >= heights[y][x]) break
        }

        return up * right * down * left
    }
}