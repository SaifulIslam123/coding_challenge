package com.courier.service.repository

import com.courier.service.model.Package

class CourierDeliveryCost(private val offerService: OfferService) {

    var baseDeliveryCost = 0

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
}