package com.courier.service.model

data class Package(val id: Int, val packageName: String, var discount: Int = 0, val weight: Int, val distance: Int, val offerCode: String? = null,
                   var deliveryTime: Double = 0.0, var deliveryCost: Int = 0)