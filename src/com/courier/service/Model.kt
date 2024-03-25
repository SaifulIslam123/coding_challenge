package com.courier.service

data class Package(val packageName: String, val weight: Int, val distance: Int, val offerCode: String? = null)
data class Offer(val offerName: String, val discountPercentage: Int, val distance: Distance, val weight: Weight)
data class Weight(val min: Int, val max: Int)
data class Distance(val min: Int, val max: Int)
data class Vehicle(val vehicleId: Int, val availableTime: Double = 0.0, val totalDeliveryTime: Double = 0.0)