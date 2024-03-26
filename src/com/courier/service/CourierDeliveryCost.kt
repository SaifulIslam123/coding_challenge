package com.courier.service

class CourierDeliveryCost {

    var baseDeliveryCost = 0
    var offerService: OfferService? = null
        set(value) {
            field = value
        }

    fun calculateDeliveryTotalCost(pack: Package): Pair<Int, Int> {

        var totalDeliveryCost = baseDeliveryCost + (pack.weight * 10) + (pack.distance * 5)
        val discount = if (pack.offerCode.isNullOrEmpty()) 0 else {
            val percent = offerService?.getDiscount(pack.offerCode, pack.distance, pack.weight)!!
            (totalDeliveryCost * percent) / 100
        }

        totalDeliveryCost -= discount
        //println("${pack.packageName} $discount $totalDeliveryCost")
        return Pair(discount, totalDeliveryCost)
    }
}