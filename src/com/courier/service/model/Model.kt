package com.courier.service.model

data class Package(val id: Int, val packageName: String, var discount: Int = 0, val weight: Int, val distance: Int, val offerCode: String? = null,
                   var deliveryTime: Double = 0.0, var deliveryCost: Int = 0)

data class Offer(val offerName: String, val discountPercentage: Int, val distance: Distance, val weight: Weight)
data class Weight(val min: Int, val max: Int)
data class Distance(val min: Int, val max: Int)
data class Vehicle(val vehicleId: Int, var availableTime: Double = 0.0)