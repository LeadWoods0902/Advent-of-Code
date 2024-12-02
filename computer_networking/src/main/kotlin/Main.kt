fun convertToONECoordinate(nodeX: Int, nodeY: Int): String {
    // Coordinates corresponding to nodeLocation (from your given data)
    val nodeLocation1 = Pair(930, 1700)  // (x, y)
    val nodeLocation2 = Pair(680, 1800)
    val nodeLocation3 = Pair(550, 1500)

    // Corresponding ONE points
    val onePoint1 = Pair(2551121.9534618445, 6672693.903576347)
    val onePoint2 = Pair(2550854.3137440286, 6672591.750129179)
    val onePoint3 = Pair(2550700.913609861, 6672866.723243455)

    // Calculate scaling factors (scaleX, scaleY) for both axes
    val scaleX = (onePoint1.first - onePoint2.first) / (nodeLocation1.first - nodeLocation2.first)
    val scaleY = (onePoint1.second - onePoint2.second) / (nodeLocation1.second - nodeLocation2.second)

    // Calculate offsets for both axes
    val offsetX = onePoint1.first - (scaleX * nodeLocation1.first)
    val offsetY = onePoint1.second - (scaleY * nodeLocation1.second)

    // Apply the transformation to the input node location
    val oneX = scaleX * nodeX + offsetX
    val oneY = scaleY * nodeY + offsetY

    // Format as ONE POINT
    return "POINT ($oneX $oneY)"
}

fun convertToNodeCoordinate(oneX: Double, oneY: Double): Pair<Int, Int> {
    // Coordinates corresponding to nodeLocation (from your given data)
    val nodeLocation1 = Pair(930, 1700)  // (x, y)
    val nodeLocation2 = Pair(680, 1800)
    val nodeLocation3 = Pair(550, 1500)

    // Corresponding ONE points
    val onePoint1 = Pair(2551121.9534618445, 6672693.903576347)
    val onePoint2 = Pair(2550854.3137440286, 6672591.750129179)
    val onePoint3 = Pair(2550700.913609861, 6672866.723243455)

    // Calculate scaling factors (scaleX, scaleY) for both axes
    val scaleX = (onePoint1.first - onePoint2.first) / (nodeLocation1.first - nodeLocation2.first)
    val scaleY = (onePoint1.second - onePoint2.second) / (nodeLocation1.second - nodeLocation2.second)

    // Calculate offsets for both axes
    val offsetX = onePoint1.first - (scaleX * nodeLocation1.first)
    val offsetY = onePoint1.second - (scaleY * nodeLocation1.second)

    // Apply the inverse transformation to the ONE coordinates
    val nodeX = ((oneX - offsetX) / scaleX).toInt()
    val nodeY = ((oneY - offsetY) / scaleY).toInt()

    // Return as a Pair of node coordinates (x, y)
    return Pair(nodeX, nodeY)
}

fun main() {
    val nodeX = 900  // Example input nodeX
    val nodeY = 700  // Example input nodeY

    val onePoint = convertToONECoordinate(nodeX, nodeY)
    val xy = convertToNodeCoordinate(2553288.8592559667, 6674230.566284847)
    println(xy)
}