package com.courier.service.repository

import com.courier.service.model.InputData
import com.courier.service.model.Package

class CourierDeliveryRepository(
        private val courierDeliveryCost: CourierDeliveryCost,
        private val courierDeliveryTime: CourierDeliveryTime
) {

    fun calculateCourierDeliveryTime(inputData: InputData): MutableList<Package> {

        with(inputData) {
            courierDeliveryCost.baseDeliveryCost = baseDeliveryCost
            courierDeliveryTime.maxCarriableWeight = maxCarriableWeight
            courierDeliveryTime.maxVehicleSpeed = maxVehicleSpeed
            courierDeliveryTime.packageList = packageList
            courierDeliveryTime.vehicleList = vehicleList
        }
        return courierDeliveryTime.calculateDeliveryTime()

    }


}