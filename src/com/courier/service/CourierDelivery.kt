package com.courier.service

import findMaxSum
import java.text.DecimalFormat

class CourierDelivery {

    private val offerService = OfferService()
    private var baseDeliveryCost = 0
    private val packageList = mutableListOf<Package>()
    private val vehicleList = mutableListOf<Vehicle>()
    private var maxVehicleSpeed = 0
    private var maxCarriableWeight = 0
    private var finalOutputPackageList = mutableListOf<Package>()

    init {
        offerService.createOffer()
    }

    fun takeInput() {
        /*println("Enter base_delivery_cost no_of_packges")
        val input = readLine()
        if (input != null && input.contains(" ")) {
            val parts = input.split(" ")
            baseDeliveryCost = parts[0].toInt()
            val numberOfPackage = parts[1].toInt()

            println("Enter pkg_id pkg_weight_in_kg distance_in_km offer_code")
            repeat(numberOfPackage) {
                val line = readLine() ?: ""
                val parts = line.split(" ")
                val packageName = parts[0]
                val weight = parts[1].toInt()
                val distance = parts[2].toInt()
                val offer = parts[3]
                packageList.add(Package(it, packageName, weight, distance, offer))
            }

            println("Enter no_of_vehicles max_speed max_carriable_weight")
            val vehicleInput = readLine()
            if (vehicleInput != null && vehicleInput.contains(" ")) {
                val inputParts = vehicleInput.split(" ")
                maxVehicleSpeed = inputParts[1].toInt()
                maxCarriableWeight = inputParts[2].toInt()

                repeat(inputParts[0].toInt()) {
                    vehicleList.add(Vehicle(it))
                }
            }

            calculateDeliveryTime()
            *//* finalOutputPackageList.forEach {
                 println("${it.packageName} ${it.deliveryCost} ${it.deliveryTime}")
             }*//*
        }*/

        setTestData()
        calculateDeliveryTime()
        finalOutputPackageList.sortBy { it.id }
        finalOutputPackageList.forEach {
            println("${it.packageName} ${it.discount} ${it.deliveryCost} ${it.deliveryTime}")
        }
    }

    fun setTestData() {
        baseDeliveryCost = 100

        packageList.add(Package(id = 0, packageName = "PKG1", weight = 50, distance = 30, offerCode = "OFR001"))
        packageList.add(Package(id = 1, packageName = "PKG2", weight = 75, distance = 125, offerCode = "OFR008"))
        packageList.add(Package(id = 2, packageName = "PKG3", weight = 175, distance = 100, offerCode = "OFR003"))
        packageList.add(Package(id = 3, packageName = "PKG4", weight = 110, distance = 60, offerCode = "OFR002"))
        packageList.add(Package(id = 4, packageName = "PKG5", weight = 155, distance = 95, offerCode = "NA"))

        vehicleList.add(Vehicle(0))
        vehicleList.add(Vehicle(1))
        maxVehicleSpeed = 70
        maxCarriableWeight = 200
    }

    fun calculateDeliveryTotalCost(pack: Package): Pair<Int, Int> {

        var totalDeliveryCost = baseDeliveryCost + (pack.weight * 10) + (pack.distance * 5)
        val discount = if (pack.offerCode.isNullOrEmpty()) 0 else {
            val percent = offerService.getDiscount(pack.offerCode, pack.distance, pack.weight)
            (totalDeliveryCost * percent) / 100
        }

        totalDeliveryCost -= discount
        //println("${pack.packageName} $discount $totalDeliveryCost")
        return Pair(discount, totalDeliveryCost)
    }


    fun calculateDeliveryTime() {
        while (packageList.size > 0) {
            val (maxWeight, selectedPackages) = findMaxSum(packageList, maxCarriableWeight)
            packageList.removeAll(selectedPackages)

            //assign vehicle
            val deliveryVehicle: Vehicle? = vehicleList.minBy { it.availableTime }

            var maxPackageDistance = 0.0
            selectedPackages.forEach { package1 ->

                val packDeliveryTime = (package1.distance.toDouble() / maxVehicleSpeed).let {
                    val decimalFormat = DecimalFormat("#.##")
                    return@let decimalFormat.format(it).toDouble()
                }

                if (packDeliveryTime > maxPackageDistance) maxPackageDistance = packDeliveryTime
                package1.deliveryTime = deliveryVehicle?.availableTime!! + packDeliveryTime
                val deliveryCostInfo = calculateDeliveryTotalCost(package1)
                package1.discount = deliveryCostInfo.first
                package1.deliveryCost = deliveryCostInfo.second

                finalOutputPackageList.add(package1)
            }
            deliveryVehicle?.let {
                it.availableTime = it.availableTime + (maxPackageDistance * 2)
            }
        }
    }


}