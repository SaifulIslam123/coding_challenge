package com.courier.service

class CourierDelivery {

    private var offerService: OfferService
    private var courierDeliveryCost: CourierDeliveryCost
    private var courierDeliveryTime: CourierDeliveryTime

    init {
        //Injectable objects
        offerService = OfferService()
        offerService.createOffer()

        courierDeliveryCost = CourierDeliveryCost()
        courierDeliveryCost.offerService = offerService

        courierDeliveryTime = CourierDeliveryTime()
        courierDeliveryTime.courierDeliveryCost = courierDeliveryCost

    }

    fun takeInput() {
        /*println("Enter base_delivery_cost no_of_packges")
        val input = readLine()
        if (input != null && input.contains(" ")) {
            val parts = input.split(" ")
            courierDeliveryCost.baseDeliveryCost = parts[0].toInt()
            val numberOfPackage = parts[1].toInt()

            println("Enter pkg_id pkg_weight_in_kg distance_in_km offer_code")
            repeat(numberOfPackage) {
                val line = readLine() ?: ""
                val parts = line.split(" ")
                val packageName = parts[0]
                val weight = parts[1].toInt()
                val distance = parts[2].toInt()
                val offer = parts[3]
                courierDeliveryTime.packageList.add(Package(id = it, packageName = packageName, weight = weight, distance = distance, offerCode = offer))
            }

            println("Enter no_of_vehicle max_speed max_carriable_weight")
            val vehicleInput = readLine()
            if (vehicleInput != null && vehicleInput.contains(" ")) {
                val inputParts = vehicleInput.split(" ")
                courierDeliveryTime.maxVehicleSpeed = inputParts[1].toInt()
                courierDeliveryTime.maxCarriableWeight = inputParts[2].toInt()

                repeat(inputParts[0].toInt()) {
                    courierDeliveryTime.vehicleList.add(Vehicle(it))
                }
            }

            courierDeliveryTime.calculateDeliveryTime()
            courierDeliveryTime.showOutput()
        }*/

        setTestData()
        courierDeliveryTime.calculateDeliveryTime()
        courierDeliveryTime.showOutput()
    }

    fun setTestData() {
        courierDeliveryCost.baseDeliveryCost = 100

        courierDeliveryTime.packageList.add(Package(id = 0, packageName = "PKG1", weight = 50, distance = 30, offerCode = "OFR001"))
        courierDeliveryTime.packageList.add(Package(id = 1, packageName = "PKG2", weight = 75, distance = 125, offerCode = "OFR008"))
        courierDeliveryTime.packageList.add(Package(id = 2, packageName = "PKG3", weight = 175, distance = 100, offerCode = "OFR003"))
        courierDeliveryTime.packageList.add(Package(id = 3, packageName = "PKG4", weight = 110, distance = 60, offerCode = "OFR002"))
        courierDeliveryTime.packageList.add(Package(id = 4, packageName = "PKG5", weight = 155, distance = 95, offerCode = "NA"))
        courierDeliveryTime.vehicleList.add(Vehicle(0))
        courierDeliveryTime.vehicleList.add(Vehicle(1))
        courierDeliveryTime.maxVehicleSpeed = 70
        courierDeliveryTime.maxCarriableWeight = 200
    }
}