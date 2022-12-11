package ca.jonathanfritz.adventofcode2022.day11

import kotlin.math.ceil
import kotlin.math.floor

class Monkeys {

    fun part1(lines: List<String>): Int {
        val monkeys = parseInput(lines)

        for (round in 0 until 20) {
            monkeys.forEach { m -> println("\nMonkey ${monkeys.indexOf(m)}"); m.evaluate(monkeys) }

            println("After Round $round:")
            monkeys.forEachIndexed { i, m ->
                println("Monkey $i: ${m.items}")
            }
            println()
        }

        monkeys.forEachIndexed { i, m ->
            println("Monkey $i inspected items ${m.inspectionCount} times")
        }

        return monkeys.map { it.inspectionCount }.sortedByDescending { it }.take(2).multiply()
    }

    private fun parseInput(lines: List<String>): List<Monkey> {
        // input is grouped into chunks of seven lines (including trailing newline)
        return (lines.indices step 7).map { offset ->
            val chunk = lines.subList(offset, offset + 6).map { it.trim() }

            // second line contains a csv list of starting item ids
            val items = chunk[1].split(":")[1].split(",").map { it.trim().toInt() }.toMutableList()

            // third line contains a mathematical expression that is always addition or multiplication of two values
            // result is always divided by 3 and rounded down to the nearest int
            val expression = chunk[2].split("=")[1].trim().split(" ").map { it.trim() }
            val operator = expression[1]
            // operand will be null if it is the string "old", non-null if it is a constant int
            val operands = listOf(expression[0].toIntOrNull(), expression[2].toIntOrNull())
            // null operands are replaced with the input value for old
            val operation = if (operator == "+") {
                { old: Int -> floor(ceil(((operands[0] ?: old) + (operands[1] ?: old)).toDouble()) / 3f ).toInt() }
            } else {
                { old: Int -> floor(ceil(((operands[0] ?: old) * (operands[1] ?: old)).toDouble()) / 3f ).toInt() }
            }

            // lines 4-5 contain a divisibility test and the monkey to throw an item to if it passes or fails
            val testAmount = chunk[3].intSuffix()
            val trueMonkey = chunk[4].intSuffix()
            val falseMonkey = chunk[5].intSuffix()
            val test = { value:Int -> if (value % testAmount == 0) trueMonkey else falseMonkey }

            // that's all the info that we need to create a Monkey
            Monkey(items, operation, test)
        }
    }

    private fun String.intSuffix() = this.split(" ").map { it.trim() }.last().toInt()

    data class Monkey(
        val items: MutableList<Int>,
        val operation: (Int) -> Int,
        val test: (Int) -> Int,
        var inspectionCount: Int = 0
    ) {
        fun evaluate(monkeys: List<Monkey>) {
            (items.indices).map { i ->
                this.inspectionCount++

                println("Inspecting item ${items[i]}")
                val worryLevel = operation.invoke(items[i])
                println("   Worry level: $worryLevel")
                val toMonkey = test.invoke(worryLevel)
                println ("   Thrown to: Monkey $toMonkey")

                Evaluation(items[i], worryLevel, toMonkey)
            }.forEach {
                // the item retains the new worry level when thrown
                monkeys[it.toMonkey].items.add(it.newWorryLevel)
                this.items.remove(it.originalWorryLevel)
            }
        }

        data class Evaluation(
            val originalWorryLevel: Int,
            val newWorryLevel: Int,
            val toMonkey: Int
        )
    }

    private fun List<Int>.multiply(): Int {
        var sum: Int = this.first()
        this.takeLast(this.size - 1).forEach {
            sum *= it
        }
        return sum
    }
}
