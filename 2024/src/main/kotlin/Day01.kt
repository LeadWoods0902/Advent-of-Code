import java.io.File
import kotlin.math.abs

fun part1(sortedLeft: List<Int>, sortedRight: List<Int>): Int {
    // sum the absolute differences between corresponding elements
    return sortedLeft.zip(sortedRight).sumOf { abs(it.first - it.second) }
}

fun part2(sortedLeft: List<Int>, sortedRight: List<Int>): Int {
    val leftBucket: MutableMap<Int, Int> = mutableMapOf()

    // count occurrences of each integer in sortedLeft
    sortedLeft.forEach { leftBucket[it] = leftBucket.getOrDefault(it, 0) + 1 }

    // multiply each integer in sortedRight by its frequency in leftBucket and sum
    return sortedRight.sumOf {
        leftBucket.getOrDefault(it, 0) * it
    }
}

/**
 * p1_solution = 1889772
 *
 * p2_solution = 23228917
 */
fun main() {
    // read input and map each line into pairs of integers
    val input = File("src/main/resources/Day01.txt").readLines()
        .map { line ->
            line.split(" ").filter { it.isNotEmpty() }.let {
                it[0].toInt() to it[1].toInt()
            }
        }

    // unzip the input into two lists and sort them
    val (sortedLeft, sortedRight) = input.unzip().let {
        it.first.sorted() to it.second.sorted()
    }

    println("Part 1 | ${part1(sortedLeft, sortedRight)}")
    println("Part 2 | ${part2(sortedLeft, sortedRight)}")
}