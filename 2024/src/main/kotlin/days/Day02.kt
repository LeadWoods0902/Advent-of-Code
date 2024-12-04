package days

import java.io.File
import kotlin.math.abs

/**
 * p1_solution = 383
 *
 * p2_solution = 436
 */
class Day02 : Day(2){
    private val input = File("src/main/resources/Day02.txt").readLines()
        .map { line ->
            line.split(" ").map{
                it.toInt()
            }
        }

    override fun part1(): Any {
        return part1(input)
    }

    private fun part1(inputReports: List<List<Int>> = input): Int {
        return inputReports.count { report ->
            val decreasing = report[1] > report[0]
            for (i in 0 until report.size - 1) {
                // Reject report if the gradient inverts
                if (decreasing == report[i] > report[i + 1]) {
                    return@count false
                }
                // Reject report if the gradient becomes unacceptable (must be within -3..3 and ≠ 0)
                if ((abs(report[i] - report[i + 1]) > 3) || (report[i] == report[i + 1])) {
                    return@count false
                }
            }
            true
        }
    }

    override fun part2(): Any {
        return input.count { report ->
            // check if the report itself passes
            if (part1(listOf(report)) > 0) return@count true

            // generate all sublists and check if any pass the criteria
            generateSubLists(report).any { sublist ->
                part1(listOf(sublist)) > 0
            }
        }
    }

    private fun generateSubLists(input: List<Int>): List<List<Int>> {
        return List(input.size) { i ->
            input.filterIndexed { j,  _->  j != i}
        }
    }
}