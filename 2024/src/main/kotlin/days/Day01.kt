package days

import java.io.File
import kotlin.math.abs

/**
 * Day 1 solution for Advent of Code.
 *
 * Part1: Computes the sum of the absolute differences between corresponding
 * integers from two sorted lists.
 *
 * Part 2: Counts the occurrences of each integer in the first sorted list and then
 * multiplies each integer in the second sorted list by its frequency in the first
 * list, summing the results.
 *
 * p1_solution = 1889772
 * p2_solution = 23228917
 */
class Day01: Day(1) {
    private val input = File("src/main/resources/Day01.txt").readLines().map { line ->
        line.split(" ").filter { it.isNotEmpty() }
    }.map { (a, b) -> a.toInt() to b.toInt() }

    private val sortedLeft = input.map { it.first }.sorted()
    private val sortedRight = input.map { it.second }.sorted()

    override fun part1(): Any {
        return sortedLeft.zip(sortedRight).sumOf { abs(it.first - it.second) }
    }

    override fun part2(): Any {
        val leftBucket: MutableMap<Int, Int> = mutableMapOf()

        // count occurrences of each integer in sortedLeft
        sortedLeft.forEach { leftBucket[it] = leftBucket.getOrDefault(it, 0) + 1 }

        // multiply each integer in sortedRight by its frequency in leftBucket and sum
        return sortedRight.sumOf {
            leftBucket.getOrDefault(it, 0) * it
        }
    }
}