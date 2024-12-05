package days

import java.io.File
import kotlin.io.path.createTempDirectory

enum class Direction(val rowDelta: Int, val colDelta: Int){
    HORIZONTAL_RIGHT(0, 1),
    VERTICAL_DOWN(1, 0),
    DIAGONAL_DOWN_RIGHT(1, 1),
    DIAGONAL_DOWN_LEFT(1, -1),
    HORIZONTAL_LEFT(0, -1),
    VERTICAL_UP(-1, 0),
    DIAGONAL_UP_LEFT(-1, -1),
    DIAGONAL_UP_RIGHT(-1, 1)
}

/**
 * Day 4 solution for Advent of Code.
 *
 * Part1:
 *
 * Part 2:
 *
 * p1_solution = 2583
 * p2_solution = 1978
 */
class Day04 : Day(4) {
    private val input = File("src/main/resources/Day04.txt").readLines()
    private val word = "XMAS"
    private val pattern = listOf(
        "M.S",
        ".A.",
        "M.S"
    )

    override fun part1() : Any {
        return input.indices.sumOf { row ->
            input[row].indices.sumOf { col ->
                Direction.entries.count{ dir ->
                    matchesWord(row, col, dir)
                }
            }
        }
    }

    override fun part2() : Any {
        return input.indices.sumOf { row ->
            input[row].indices.sumOf { col ->
                Direction.entries.count{ dir ->
                    matchesPattern(row, col, dir)
                }
            }
        }
    }

    private fun matchesWord(row: Int, col: Int, dir: Direction): Boolean{
        for(i in word.indices){
            val r = row + i  * dir.rowDelta
            val c = col + i * dir.colDelta
            if(r !in input.indices || c !in input[r].indices || input[r][c] != word[i]) return false
        }
        return true
    }

    private fun matchesPattern(row: Int, col: Int, dir: Direction): Boolean{
        for(pRow in pattern.indices) {
            for(pCol in pattern[pRow].indices){
                val r = row+pRow * dir.rowDelta + pCol * dir.colDelta
                val c = col+pRow * dir.colDelta + pCol * dir.rowDelta
                if (r !in input.indices || c !in input[r].indices) return false

                val pChar = pattern[pRow][pCol]
                if(pChar != '.' && input[r][c] != pChar) return false
            }
        }
        return true
    }

}

fun main() {
    val day = Day04()
    println(day.part1())
}