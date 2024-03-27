package com.courier.service.model

class InputData {
    var baseDeliveryCost = 0
    val packageList = mutableListOf<Package>()
    val vehicleList = mutableListOf<Vehicle>()
    var maxVehicleSpeed = 0
    var maxCarriableWeight = 0
}