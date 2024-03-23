package com.courier.service

fun main() {

    val obj = CourierService()
    obj.takeInput()

}

class CourierService {

    private val offerService = OfferService()
    private var baseDeliveryCost = 0

    fun takeInput() {
        println("Enter the deliverycost totalpackage")
        val input = readLine()
        if (input != null && input.contains(" ")) {
            val parts = input.split(" ")
            baseDeliveryCost = parts[0].toInt()
            val numberOfPackage = parts[1].toInt()
            val packageList = mutableListOf<String>()
            println("Enter packages: ")
            repeat(numberOfPackage) {
                val line = readLine() ?: ""
                packageList.add(line)
            }

            // Print the inputs
            println("You entered $numberOfPackage lines:")
            packageList.forEachIndexed { index, input ->
                println("${baseDeliveryCost} Line ${index + 1}: $input")
            }
        }
    }

    fun totalDeliveryCost() {
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