package days

import java.io.File
import kotlin.math.abs

/**
 * Day 2 solution for Advent of Code.
 *
 * Part 1: Counts how many reports are valid based on a set of rules. A report is
 *  valid if the gradient stays within the -3 to 3 range and no consecutive values
 *  are equal.
 *
 * Part 2: Counts how many reports are valid, including the additional rule that a
 *  report is valid if it becomes safe by removing at most one value.
 *
 * p1_solution = 383
 *
 * p2_solution = 436
 */
class Day02 : Day(2) {
    private val input = File("src/main/resources/Day02.txt").readLines()
        .map { line ->
            line.split(" ").map { it.toInt() }
        }

    override fun part1(): Any {
        return input.count { report -> isSafeReport(report) }
    }

    override fun part2(): Any {
        return input.count { report ->
            // Check if the report itself passes
            if (isSafeReport(report)) return@count true

            // Generate all sub-lists and check if any pass the criteria
            generateSubLists(report).any { sublist ->
                isSafeReport(sublist)
            }
        }
    }

    // A helper method to validate a report
    private fun isSafeReport(report: List<Int>): Boolean {
        val decreasing = report[1] > report[0]
        for (i in 0 until report.size - 1) {
            // Reject report if the gradient inverts
            if (decreasing == report[i] > report[i + 1]) {
                return false
            }
            // Reject report if the gradient becomes unacceptable (must be within -3..3 and ≠ 0)
            if ((abs(report[i] - report[i + 1]) > 3) || (report[i] == report[i + 1])) {
                return false
            }
        }
        return true
    }

    // A helper method to generate all sub-lists of length n-1
    private fun generateSubLists(input: List<Int>): List<List<Int>> {
        return List(input.size) { i ->
            input.filterIndexed { j, _ -> j != i }
        }
    }
}