package com.courier.service

class CourierDelivery {

    private val offerService = OfferService()
    private var baseDeliveryCost = 0
    private val packageList = mutableListOf<Package>()
    private val vehicleList = mutableListOf<Vehicle>()
    private var maxVehicleSpeed = 0
    private var maxCarriableWeight = 0

    init {
        offerService.createOffer()
    }

    fun takeInput() {
        println("Enter base_delivery_cost no_of_packges")
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
                packageList.add(Package(packageName, weight, distance, offer))
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

            // Print the inputs
            //println("You entered $numberOfPackage lines:")
            packageList.forEachIndexed { index, input ->
                //println("${baseDeliveryCost} Line ${index + 1}: $input")
                calculateTotalDeliveryCost(input)
            }
        }
    }


    fun calculateTotalDeliveryCost(pack: Package) {

        var totalDeliveryCost = baseDeliveryCost + (pack.weight * 10) + (pack.distance * 5)
        val discount = if (pack.offerCode.isNullOrEmpty()) 0 else {
            val percent = offerService.getDiscount(pack.offerCode, pack.distance, pack.weight)
            (totalDeliveryCost * percent) / 100
        }

        totalDeliveryCost -= discount
        println("${pack.packageName} $discount $totalDeliveryCost")
    }


}