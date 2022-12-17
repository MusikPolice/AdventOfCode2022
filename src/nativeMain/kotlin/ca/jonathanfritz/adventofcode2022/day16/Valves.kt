package ca.jonathanfritz.adventofcode2022.day16

class Valves {

    fun part1(lines: List<String>): Int {
        val options = Stack(
            State(
                parseInput(lines).associateBy { it.name },
                "AA",
                0,
                30,
                emptyList()
            )
        )

        // pop off and evaluate each option
        while (options.isNotEmpty()) {
            val currentState = options.popLargest{ state: State -> state.pressureReleased }

            // if time is out we're done with this path
            if (currentState.remainingTime == 0) {
                if (currentState.pressureReleased > 0) {
                    println("Released ${currentState.pressureReleased} following path ${currentState.path}")
                }
                continue
            }

            val currentValve = currentState.position
            val remainingTime = currentState.remainingTime-1

            // if the valve isn't open, we have the option to turn it on
            if (!currentValve.open && currentValve.flowRate > 0) {
                val valveState = currentState.valves.toMutableMap()
                valveState[currentValve.name]!!.open = true
                options.push(
                    State(
                        valveState,
                        currentValve.name,
                        currentState.pressureReleased + (currentValve.flowRate * remainingTime),
                        remainingTime,
                        currentState.path
                    )
                )
            }

            // go down one of the tunnels
            for (tunnel in currentValve.tunnels) {
                if (currentState.path.takeLast(2).contains(tunnel)) {
                    // don't revisit a valve that we recently came from
                    continue
                }
                options.push(
                    State(
                        currentState.valves,
                        tunnel,
                        currentState.pressureReleased,
                        remainingTime,
                        currentState.path.plus(currentValve.name)
                    )
                )
            }
        }

        return 0
    }

    data class State(
        val valves: Map<String, Valve>,
        private val pos: String,
        val pressureReleased: Int,
        val remainingTime: Int,
        val path: List<String>
    ) {
        val position = valves[pos]!!
    }

    private fun parseInput(lines: List<String>): List<Valve> {
        val lineLookup = lines.map {
            it.removePrefix("Valve ")
        }.associate { line ->
            line.substring(0, 2) to line.substring(17 ).trim()
        }

        return lineLookup.map { entry ->
            val name = entry.key
            val rate = entry.value.substring(0, entry.value.indexOf(";")).toInt()
            var offset = entry.value.indexOf("valves ") + 7
            if (offset == 6) offset = entry.value.indexOf("valve ") + 6
            val otherKeys = entry.value.substring(offset).split(",").map { it.trim() }
            Valve(name, rate, otherKeys)
        }
    }

    data class Valve (
        val name: String,
        val flowRate: Int,
        val tunnels: List<String>,
        var open: Boolean = false,
    )

    class Stack<T>(vararg initial: T) {
        private val internal: MutableList<T> = initial.toMutableList()
        fun push(entity: T) = internal.add(0, entity)
        fun pop(): T? = if (internal.isNotEmpty()) internal.removeAt(0) else null
        fun popLargest(evaluator: (t: T) -> Int): T {
            val s = internal.maxByOrNull { evaluator.invoke(it) }!!
            internal.remove(s)
            return s
        }
        fun isNotEmpty() = internal.isNotEmpty()
        override fun toString(): String {
            return "t$internal"
        }
    }
}