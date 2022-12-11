package ca.jonathanfritz.adventofcode2022.day10

class Crt {

    fun part1(lines: List<String>): Int {
        var sp = 0          // stack pointer tells us which instruction to execute
        var x = 1           // we're tracking the contents of the x register
        var cycle = 1       // the number of cycles that the machine has executed since startup
        var sleep = false   // true if we should sleep for this cycle to account for addx taking multiple cycles to complete
        var signalStrengthSum = 0

        while (true) {
            println()
            println("start of cycle:${cycle}, x:$x, sleep:$sleep, sp:$sp")
            if (cycle == 20 || (cycle - 20) % 40 == 0) {
                println("Signal strength on cycle $cycle is ${x * cycle}")
                signalStrengthSum += (x * cycle)
            }
            val instruction = lines[sp]
            if (instruction == "noop") {
                cycle++
                sp++
                if (sp == lines.size) {
                    println("End of instructions. Break.")
                    break
                }
                println("Encountered noop. Incremented cycle to $cycle and stack pointer to $sp")
                continue
            } else {
                val amount = instruction.split(" ")[1].toInt()
                sleep = !sleep
                if (!sleep) {
                    x += amount
                    sp++
                    println("added $amount to x. Result is $x")
                    if (sp == lines.size) {
                        println("End of instructions. Break.")
                        break
                    }
                } else {
                    println("addx sleeps for one cycle")
                }
                cycle++
                continue
            }
        }

        return signalStrengthSum
    }

}