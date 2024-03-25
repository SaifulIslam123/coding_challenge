package com.courier.service

fun main() {

    val obj = CourierDelivery()
    obj.takeInput()

}

fun findCombinations(array: IntArray, target: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    backtrack(array.sortedArray(), target, 0, mutableListOf(), result)
    return result
}

fun backtrack(array: IntArray, target: Int, startIndex: Int, path: MutableList<Int>, result: MutableList<List<Int>>) {
    if (target < 0) {
        return // Stop if target becomes negative
    }

    result.add(path.toList()) // Add current combination even if it exceeds target (handled later)

    for (i in startIndex until array.size) {
        path.add(array[i])
        backtrack(array, target - array[i], i + 1, path, result)
        path.removeAt(path.size - 1) // Backtrack
    }
}

/*
fun main() {
    val array = intArrayOf(50,75,175,110,155)
    val target = 200
    val combinations = findCombinations(array, target)

    println("Combinations with sum less than or equal to $target:")
    combinations.forEach { combination ->
        val sum = combination.sum()
        if (sum <= target) {
            println("$combination (sum: $sum)")
        }
    }

    */
/*while (rootPackageArray.size>=0){
        1/ find all combinations
        2/ check the highest pair or single package
        3/ Remove this packages or single packages from the root total packages
        4/ find which vehicle is now available (compare all vehicles available time and pick the less value one)
        5/ calculate all deliver packages time and vehicle next available timee
        6/ save this output to map based on package object
    }*//*


}
*/

