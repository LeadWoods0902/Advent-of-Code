package days

import java.io.File

/**
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