import java.io.File

fun main() {
    val depths = File("src/main/resources/Day01.txt").readLines().map { it.toInt() }

    var singleIncreases = 0
    for (i in 1 until depths.size) {
        if (depths[i] > depths[i - 1])
            singleIncreases += 1
    }
    println("Part 1 | $singleIncreases")

    var slidingIncreases = 0
    for (i in 2 until depths.size-1) {
        if ((depths[i]+depths[i-1]+depths[i-2]) < (depths[i-1]+depths[i]+depths[i+1])){
            slidingIncreases += 1
        }

    }
    println("Part 2 | $slidingIncreases")
}