import java.io.File


fun main() {
    val data = File("src/main/resources/Day02.txt").readLines()
        .map {
            val parts = it.split(" ")
            parts[0] to parts[1].toInt()
        }

    var horizontalPosition = 0
    var depth = 0
    var aim = 0

    for((direction, value) in data){
        when(direction){
            "forward" -> {horizontalPosition+=value}
            "down" -> {depth+=value}
            "up" -> {depth-=value}
        }
    }
    println("Part 1 | ${horizontalPosition*depth}")

    horizontalPosition = 0
    depth = 0

    for((direction, value) in data){
        when(direction){
            "forward" -> {
                horizontalPosition+=value
                depth+=value*aim
            }
            "down" -> {aim+=value}
            "up" -> {aim-=value}
        }
    }

    println("Part 2 | ${horizontalPosition * depth}")
}