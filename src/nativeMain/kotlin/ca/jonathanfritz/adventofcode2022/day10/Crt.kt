package ca.jonathanfritz.adventofcode2022.day10

import kotlin.math.ceil

class Crt {

    fun part1(lines: List<String>): Int {
        var sp = 0          // stack pointer tells us which instruction to execute
        var x = 1           // we're tracking the contents of the x register
        var cycle = 1       // the number of cycles that the machine has executed since startup
        var sleep = false   // true if we should sleep for this cycle to account for addx taking multiple cycles to complete
        var signalStrengthSum = 0

        while (true) {
            if (cycle == 20 || (cycle - 20) % 40 == 0) {
                signalStrengthSum += (x * cycle)
            }
            val instruction = lines[sp]
            if (instruction == "noop") {
                cycle++
                sp++
                if (sp == lines.size) {
                    break
                }
                continue
            } else {
                val amount = instruction.split(" ")[1].toInt()
                sleep = !sleep
                if (!sleep) {
                    x += amount
                    sp++
                    if (sp == lines.size) {
                        break
                    }
                }
                cycle++
                continue
            }
        }

        return signalStrengthSum
    }

    fun part2(lines: List<String>): Int {
        var sp = 0          // stack pointer tells us which instruction to execute
        var x = 1           // we're tracking the contents of the x register
        var cycle = 1       // the number of cycles that the machine has executed since startup
        var sleep = false   // true if we should sleep for this cycle to account for addx taking multiple cycles to complete

        // the crt has 6 rows each 40 columns wide
        val crt: MutableList<List<Int>> = mutableListOf()
        val sb = StringBuilder()

        while (true) {

            // draw to crt
            val col = (cycle - 1) % 40
            if ((cycle - 1) % 40 == 0) {
                sb.appendLine()
            }
            if (x >= (col - 1) && x <= (col + 1)) {
                sb.append("#")
            } else {
                sb.append(".")
            }

            val instruction = lines[sp]
            if (instruction == "noop") {
                cycle++
                sp++
                if (sp == lines.size) {
                    break
                }
                continue
            } else {
                val amount = instruction.split(" ")[1].toInt()
                sleep = !sleep
                if (!sleep) {
                    x += amount
                    sp++
                    if (sp == lines.size) {
                        break
                    }
                }
                cycle++
                continue
            }
        }

        println(sb.toString())

        return 0
    }

}