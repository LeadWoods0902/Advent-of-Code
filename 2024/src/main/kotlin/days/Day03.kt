package days

import java.io.File

/**
 * Day 3 solution for Advent of Code.
 *
 * Part 1: Computes the sum of the products of pairs of integers found in using
 * the regex pattern "mul([Int],[Int])".
 *
 * Part 2: Computes the sum of the products of pairs of integers as before, but
 * only when "do()" is encountered in the input. If "don't()" is encountered,
 * multiplication is skipped until "do()" is seen again.
 *
 * p1_solution = 183669043
 *
 * p2_solution = 59097164
 */
class Day03 : Day(3){
    private val input = File("src/main/resources/Day03.txt").readLines()

    override fun part1(): Int{
        val regex="""mul\((\d+),(\d+)\)""".toRegex()
        var sum =0
        input.forEach { line ->
            regex.findAll(line).forEach { matchResult ->
                val (first, second) = matchResult.destructured
                sum += first.toInt() * second.toInt()
            }
        }
        return sum
    }

    override fun part2(): Int{
        val regex="""mul\((\d+),(\d+)\)|do\(\)|don't\(\)""".toRegex()
        var sum=0
        var counting = true
        input.forEach { line ->
            regex.findAll(line).forEach { matchResult->
                when(matchResult.value){
                    "do()" -> { counting = true}
                    "don't()" -> { counting = false }
                    else -> {
                        if(counting){
                            val (first, second) = matchResult.destructured
                            sum += first.toInt() * second.toInt()
                        }
                    }
                }
            }
        }
        return sum
    }
}