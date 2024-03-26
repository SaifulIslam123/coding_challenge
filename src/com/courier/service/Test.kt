import com.courier.service.CourierDelivery
import com.courier.service.Package

fun findMaxSum(packages: List<Package>, totalWeight: Int): Pair<Int, List<Package>> {
    val n = packages.size
    val dp = Array(totalWeight + 1) { 0 }
    val selectedPackages = Array(totalWeight + 1) { mutableListOf<Package>() }

    for (i in 0 until n) {
        for (j in totalWeight downTo packages[i].weight) {
            if (dp[j] < dp[j - packages[i].weight] + packages[i].weight) {
                dp[j] = dp[j - packages[i].weight] + packages[i].weight
                selectedPackages[j] = selectedPackages[j - packages[i].weight].toMutableList().apply { add(packages[i]) }
            }
        }
    }

    val maxWeight = dp[totalWeight]
    val selectedItems = selectedPackages[totalWeight]
    return maxWeight to selectedItems
}

fun main() {
   /* val packages = listOf(
            *//*Package("Package 1", 50, 100),
            Package("Package 2", 75, 200),
            Package("Package 6", 75, 200),*//*
            Package(1, "Package 3", 175, 300)
            *//*Package("Package 4", 110, 300),
            Package("Package 5", 155, 300)*//*
    )
    val totalWeight = 200
    val (maxWeight, selectedPackages) = findMaxSum(packages, totalWeight)
    println("Maximum weight of packages less than or equal to $totalWeight is: $maxWeight")
    println("Selected packages contributing to the weight: $selectedPackages")

    val numbers = listOf(
            Package(1, "Package 2", 2, 200),
            Package(1, "Package 6", 0, 200),
            Package(1, "Package 7", 0, 200),
            Package(1, "Package 1", 50, 100),
            Package(1, "Package 3", 175, 300),
            Package(1, "Package 4", 110, 300),
            Package(1, "Package 5", 155, 300)
    )
    val lowestNumber = numbers.minBy { it.weight }
    println(" lowestNumber $lowestNumber")

    var pweight = 0
    numbers.forEach {
        if (it.weight > pweight)
            pweight = it.weight
    }

    println(" pweight $pweight")*/

    val obj = CourierDelivery()
    obj.takeInput()

}
