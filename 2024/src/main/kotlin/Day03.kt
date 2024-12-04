import java.io.File

fun part1(input: List<String>): Int{
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

fun part2(input: List<String>): Int{
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

/**
 * p1_solution = 183669043
 *
 * p2_solution = 59097164
 */
fun main(){
    val input = File("src/main/resources/Day03.txt").readLines()

    println("Part 1 | ${part1(input)}")
    println("Part 2 | ${part2(input)}")
}