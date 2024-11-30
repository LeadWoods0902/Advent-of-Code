import java.io.File

class BingoBoard {
    private val board = ArrayList<ArrayList<Pair<Int, Boolean>>>()

    fun addRowToBoard(row: List<Int>){
        board.add(ArrayList(row.map { it to false }))
    }

    fun markValue(value: Int){
        for(row in board){
            for(index in row.indices){
                if(row[index].first == value)
                    row[index] = row[index].copy(row[index].first, true)
            }
        }
    }

    fun isComplete(): Int? {
        // Check rows for completeness
        for (row in board) {
            if (row.all { it.second }) {  // If all values in the row are marked (second value is true)
                return row.sumOf { it.first }  // Return the sum of the Int values in the row
            }
        }

        // Check columns for completeness
        for (colIndex in board[0].indices) {  // Iterate through each column
            if (board.all { it[colIndex].second }) {  // If all rows in this column are marked
                return board.sumOf { it[colIndex].first }  // Return the sum of the Int values in the column
            }
        }

        // If no complete row or column is found, return -1
        return null
    }

}

fun main() {
    val data = File("src/main/resources/Day04.txt").readLines()

    // Generate the Bingo Call Order
    val bingoRollCall: List<Int> = data.first().split(",").map { it.trim().toInt() }

    // Generate all Bingo Boards
    val bingoBoards = arrayListOf<BingoBoard>()
    var currentBoard: BingoBoard? = null
    var score = 0

    for(line in data.drop(1)) {
        if(line.isBlank()){
            if(currentBoard != null) {
                bingoBoards.add(currentBoard)
            }
            currentBoard = BingoBoard()
        } else {
            val row = line.trim().split(Regex("\\s+")).map { it.toInt() }
            currentBoard?.addRowToBoard(row)
        }
    }

    currentBoard?.let { bingoBoards.add(it) }


    outer@ for (call in bingoRollCall) {
        for (board in bingoBoards) {
            board.markValue(call)
            val result = board.isComplete()
            if (result != null) {
                score = result * call
                break@outer
            }
        }
    }

    println("Part 1 | $score")
}