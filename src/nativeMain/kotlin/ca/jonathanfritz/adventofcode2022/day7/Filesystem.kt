package ca.jonathanfritz.adventofcode2022.day7

class Filesystem {

    fun part1(lines: List<String>): Long {
        // parse the directory tree
        val root: Directory = parse(lines)

        // walk the tree, summing up the sizes of any dir whose size is <= 100k
        // boy do I wish I were better at recursion :/
        var sum: Long = 0
        val toScan: MutableList<Directory> = mutableListOf(root)
        while (toScan.isNotEmpty()) {
            val dir = toScan.removeAt(0)
            val size = dir.size()
            if (size <= 100000) {
                sum += size
            }
            toScan.addAll(dir.directories)
        }

        return sum
    }

    private fun parse(lines: List<String>): Directory {
        val root = Directory("/")
        var dir = root

        // skip the initial `$ cd /` command: root is the top-level directory by definition
        var pointer = 1
        while (pointer < lines.size) {
            val command = lines[pointer]
            if (command.startsWith("$ ls")) {
                val contents = lines.sublist(pointer, "$")
                contents.forEach { obj ->
                    val split = obj.split(" ").map { it.trim() }
                    if (obj.startsWith("dir")) {
                        dir.directories.add(Directory(split[1], dir))
                    } else {
                        dir.files.add(File(split[1], split[0].toLong()))
                    }
                }
                pointer += contents.size
            } else if (command.startsWith("$ cd")) {
                dir = if (command.endsWith("..")) {
                    dir.parent!!
                } else {
                    val name = command.split(" ")[2]
                    dir.directories.first { it.name == name }
                }
            }
            pointer++
        }

        return root
    }

    private fun List<String>.sublist(start: Int, untilPrefix: String): List<String> {
        return this.drop(start + 1).takeWhile { !it.startsWith(untilPrefix) }
    }

    data class Directory(
        val name: String,
        val parent: Directory? = null,
        val directories: MutableList<Directory> = mutableListOf(),
        val files: MutableList<File> = mutableListOf()
    ) {

        fun size(): Long {
            return files.sumOf { it.size } + directories.sumOf { it.size() }
        }

        fun print(indent: Int = 0) {
            println((0 until indent).joinToString("") { " " } + "- $name (dir, size=${size()})")
            directories.forEach { it.print(indent + 2) }
            files.forEach { it.print(indent + 2) }
        }

        override fun toString(): String {
            return "$name (dir, size=${size()})"
        }
    }

    data class File(
        val name: String,
        val size: Long
    ) {
        fun print(indent: Int = 0) {
            println((0 until indent).joinToString("") { " " } + "- $name (file, size=$size)")
        }
    }
}
