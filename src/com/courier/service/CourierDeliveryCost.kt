package com.courier.service

class CourierDeliveryCost {

    private val offerService = OfferService()
    private var baseDeliveryCost = 0

    fun takeInput() {
        println("Enter base_delivery_cost no_of_packges")
        val input = readLine()
        if (input != null && input.contains(" ")) {
            val parts = input.split(" ")
            baseDeliveryCost = parts[0].toInt()
            val numberOfPackage = parts[1].toInt()
            val packageList = mutableListOf<String>()
            println("Enter pkg_id1 pkg_weight1_in_kg distance1_in_km offer_code1")
            repeat(numberOfPackage) {
                val line = readLine() ?: ""
                packageList.add(line)
            }

            // Print the inputs
            //println("You entered $numberOfPackage lines:")
            packageList.forEachIndexed { index, input ->
                //println("${baseDeliveryCost} Line ${index + 1}: $input")
                calculateTotalDeliveryCost(input)
            }
        }
    }


    fun calculateTotalDeliveryCost(input: String) {
        val parts = input.split(" ")
        val weight = parts[1].toInt()
        val distance = parts[2].toInt()
        val offer = parts[3]
        val discount = if (offer.isEmpty()) 0 else offerService.getDiscount(offer, distance, weight)

        var totalDeliveryCost = baseDeliveryCost + (weight * 10) + (distance * 5)

        totalDeliveryCost -= (totalDeliveryCost % discount)

        println("${parts[0]} $discount $totalDeliveryCost")
    }


    fun createOffer() {
        val offerList = arrayListOf<Offer>(
                Offer("ofr001", 10, Distance(0, 200), Weight(70, 200)),
                Offer("ofr002", 7, Distance(50, 150), Weight(100, 250)),
                Offer("ofr003", 5, Distance(50, 250), Weight(10, 150))
        )
        offerService.addOffer(offerList)
    }

}