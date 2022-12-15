package ca.jonathanfritz.adventofcode2022.day14

class FallingSand {

    fun part1(lines: List<String>): Int {
        val (maxYOfRock, grid) = parseInput(lines)
        val sandSpawn = Point(500, 0)

        var unitsOfSand = 0
        while (true) {
            var sand = Sand(sandSpawn)
            unitsOfSand++
            while (true) {
                if (sand.position.y > maxYOfRock) {
                    println("${unitsOfSand - 1} units of sand were dropped before they started to fall into the abyss")
                    return unitsOfSand - 1
                }

                val newSand = sand.move(grid)
                if (newSand != null) {
                    sand = newSand
                } else {
                    // sand has come to a steady state - place it within the grid
                    grid.put(sand)
                    break
                }
            }
        }
    }

    fun part2(lines: List<String>): Int {
        // sand no longer falls into the abyss
        val (_, grid) = parseInput(lines, abyss = false)
        val sandSpawn = Point(500, 0)

        var unitsOfSand = 0
        while (true) {
            var sand = Sand(sandSpawn)
            unitsOfSand++
            if (grid.entityExistsBelow(sand.position) && grid.entityExistsBelowLeft(sand.position) && grid.entityExistsBelowRight(sand.position)) {
                // no minus one b/c we have to account for sand resting at the spawn point
                println("$unitsOfSand units of sand were dropped before there was no room for more")
                return unitsOfSand
            }

            while (true) {
                val newSand = sand.move(grid)
                if (newSand != null) {
                    sand = newSand
                } else {
                    // sand has come to a steady state - place it within the grid
                    grid.put(sand)
                    break
                }
            }
        }
    }

    private fun parseInput(
        lines: List<String>,
        abyss: Boolean = true
    ): Pair<Int, Grid> {
        val grid = Grid()
        var maxYOfRock = Int.MIN_VALUE
        for (line in lines) {
            // each line describes a rock made up of a series of nodes
            val nodes = line.split(" -> ").map { it.trim() }
            var prev: Point? = null
            for (node in nodes) {
                // each node contains coordinates in [x,y] notation
                val pos = node.split(",").map { it.trim().toInt() }
                val cur = Point(pos[0], pos[1])

                // need to track the max y level of rock so we can detect when sand is falling into the abyss
                if (cur.y > maxYOfRock) {
                    maxYOfRock = cur.y
                }

                if (prev != null) {
                    if (prev.x == cur.x) {
                        if (prev.y < cur.y) {
                            // moving downward
                            for (y in (prev.y..cur.y)) {
                                grid.put(Rock(prev.x, y))
                            }
                        } else {
                            // moving upward
                            for (y in (prev.y downTo cur.y)) {
                                grid.put(Rock(prev.x, y))
                            }
                        }
                    } else if (prev.y == cur.y) {
                        if (prev.x < cur.x) {
                            // moving to the right
                            for (x in (prev.x..cur.x)) {
                                grid.put(Rock(x, prev.y))
                            }
                        } else {
                            // moving to the left
                            for (x in (prev.x downTo cur.x)) {
                                grid.put(Rock(x, prev.y))
                            }
                        }
                    } else {
                        // diagonals aren't supported... i hope
                        throw IllegalArgumentException("Unexpected diagonal rocks")
                    }
                }
                prev = cur
            }
        }
        if (!abyss) {
            grid.setFloorHeight(maxYOfRock + 2)
        }
        return maxYOfRock to grid
    }

    abstract class Entity (
        val position: Point
    )

    class Rock(
        x: Int,
        y: Int
    ): Entity(Point(x, y))

    class Sand(
        position: Point
    ): Entity(position) {
        fun move(grid: Grid): Sand? {
            return if (!grid.entityExistsBelow(this.position)) {
                Sand(position.moveDown())
            } else if (!grid.entityExistsBelowLeft(this.position)) {
                Sand(position.moveDownLeft())
            } else if (!grid.entityExistsBelowRight(this.position)) {
                Sand(position.moveDownRight())
            } else {
                // can't move
                return null
            }
        }
    }

    data class Point(
        val x: Int,
        val y: Int
    ) {
        fun moveDown(): Point = this.copy(y = y+1)
        fun moveDownLeft(): Point = this.copy(y = y+1, x = x-1)
        fun moveDownRight(): Point = this.copy(y = y+1, x = x+1)
    }

    class Grid {
        // grid is a map of x to y to a nullable entity that could be either rock or sand
        private val _grid: MutableMap<Int, MutableMap<Int, Entity?>> = mutableMapOf()
        private var floorY: Int? = null

        fun put(entity: Entity) {
            val row = _grid[entity.position.x]
            if (row == null) {
                _grid[entity.position.x] = mutableMapOf(entity.position.y to entity)
            } else {
                row[entity.position.y] = entity
                _grid[entity.position.x] = row
            }
        }

        fun setFloorHeight(y: Int) {
            println("Set floor height to $y")
            floorY = y
        }

        fun entityExistsBelow(point: Point): Boolean {
            val entity = _grid[point.x]?.get(point.y+1) != null
            val floor = point.y+1 >= (floorY ?: Int.MAX_VALUE)
            return entity || floor
        }
        fun entityExistsBelowLeft(point: Point): Boolean {
            val entity = _grid[point.x-1]?.get(point.y+1) != null
            val floor = point.y+1 >= (floorY ?: Int.MAX_VALUE)
            return entity || floor
        }
        fun entityExistsBelowRight(point: Point): Boolean {
            val entity = _grid[point.x+1]?.get(point.y+1) != null
            val floor = point.y+1 >= (floorY ?: Int.MAX_VALUE)
            return entity || floor
        }
    }
}