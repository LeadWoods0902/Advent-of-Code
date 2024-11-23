import java.io.File

// Helper function to filter data based on the majority value in a column
fun filterDataBasedOnColumn(data: List<String>, column: Int, preferOnes: Boolean): List<String> {
    val (ones, zeros) = data.fold(0 to 0) { (ones, zeros), line ->
        if (line[column] == '1') ones + 1 to zeros else ones to zeros + 1
    }

    return when {
        zeros > ones -> data.filter { it[column] == if (preferOnes) '0' else '1' }
        ones > zeros -> data.filter { it[column] == if (preferOnes) '1' else '0' }
        else -> data.filter { it[column] == if (preferOnes) '1' else '0' } // In case of a tie, prefer '1' for oxygen and '0' for carbon
    }
}

fun main() {
    // Part 1: Compute
    val data = File("src/main/resources/Day03.txt").readLines().map { it }
    val numColumns = 12
    val buckets = IntArray(numColumns) { 0 }
    var gamma = 0
    var epsilon = 0

    for(line in data){
        for((index, char) in line.withIndex()){
            buckets[index]+= (2*char.digitToInt())-1
        }
    }

    // Compute gamma and epsilon in one pass using shl and or
    buckets.forEach { bucket ->
        gamma = (gamma shl 1) or if (bucket > 0) 1 else 0
        epsilon = (epsilon shl 1) or if (bucket <= 0) 1 else 0
    }

    println("Part 1 | ${gamma*epsilon} | gamma: $gamma, epsilon: $epsilon")

    var oxygenData = data
    var carbonData = data

    // Process each column (0 to 11, for 12-digit binary numbers)
    for (column in 0 until numColumns) {
        // If only one row remains for both oxygenData and carbonData, exit early
        if (oxygenData.size == 1 && carbonData.size == 1) break

        // Process oxygenData filtering
        if (oxygenData.size > 1) {
            oxygenData = filterDataBasedOnColumn(oxygenData, column, preferOnes = true)
        }

        // Process carbonData filtering
        if (carbonData.size > 1) {
            carbonData = filterDataBasedOnColumn(carbonData, column, preferOnes = false)
        }
    }

    // Convert the binary-string value into an integer
    val oxygen: Int = oxygenData[0].toInt(2)
    val carbon: Int = carbonData[0].toInt(2)

    println("Part 2 | ${oxygen*carbon} | oxygen: $oxygen, carbon: $carbon")
}