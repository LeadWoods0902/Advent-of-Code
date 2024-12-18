package days

import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.measureTimedValue

sealed class Day(val number: Int) {

    fun solveTimed(): Duration {
        val (part1, timeP1) = measureTimedValue { part1() }
        println("\tPart 1: $part1 in ${timeP1.toString(DurationUnit.SECONDS, 4)}")
        val (part2, timeP2) = measureTimedValue { part2() }
        println("\tPart 2: $part2 in ${timeP2.toString(DurationUnit.SECONDS, 4)}")

        return timeP1 + timeP2
    }

    abstract fun part1(): Any
    abstract fun part2(): Any
}