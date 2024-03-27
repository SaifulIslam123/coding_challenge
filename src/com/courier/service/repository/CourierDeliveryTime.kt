package com.courier.service.repository

import com.courier.service.model.Package
import com.courier.service.model.Vehicle

class CourierDeliveryTime(private val courierDeliveryCost: CourierDeliveryCost) {

    var packageList = mutableListOf<Package>()
    var vehicleList = mutableListOf<Vehicle>()
    var maxVehicleSpeed = 0
    var maxCarriableWeight = 0
    // private var finalOutputPackageList = mutableListOf<Package>()

    fun calculateDeliveryTime(): MutableList<Package> {

        val finalOutputPackageList = mutableListOf<Package>()

        while (packageList.size > 0) {
            val (maxWeight, selectedPackages) = getMaxWeightPackageList(packageList, maxCarriableWeight)
            packageList.removeAll(selectedPackages)

            //assign vehicle
            val deliveryVehicle: Vehicle? = vehicleList.minBy { it.availableTime }

            var maxPackageDistance = 0.0
            selectedPackages.forEach { package1 ->

                val packDeliveryTime = (package1.distance.toDouble() / maxVehicleSpeed).let {
                    val decimalFormat = java.text.DecimalFormat("#.##")
                    return@let decimalFormat.format(it).toDouble()
                }

                if (packDeliveryTime > maxPackageDistance) maxPackageDistance = packDeliveryTime
                package1.deliveryTime = deliveryVehicle?.availableTime!! + packDeliveryTime
                val deliveryCostInfo = courierDeliveryCost.calculateDeliveryTotalCost(package1)
                package1.discount = deliveryCostInfo.first
                package1.deliveryCost = deliveryCostInfo.second

                finalOutputPackageList.add(package1)
            }
            deliveryVehicle?.let {
                it.availableTime = it.availableTime + (maxPackageDistance * 2)
            }

            /* println(finalOutputPackageList)
             println(deliveryVehicle?.availableTime)
             println("-----------------")*/
        }

        return finalOutputPackageList
    }

    private fun getMaxWeightPackageList(packages: List<Package>, totalWeight: Int): Pair<Int, List<Package>> {
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

}