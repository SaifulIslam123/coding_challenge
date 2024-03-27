package com.courier.service.repository

import com.courier.service.CourierDeliveryCost
import com.courier.service.CourierDeliveryTime
import com.courier.service.model.InputData

class CourierDeliveryRepository(
        private val courierDeliveryCost: CourierDeliveryCost,
        private val courierDeliveryTime: CourierDeliveryTime
) {

    public fun calculateCourierDeliveryTime(inputData: InputData) {

        with(inputData) {
            courierDeliveryCost.baseDeliveryCost = baseDeliveryCost
            courierDeliveryTime.maxCarriableWeight = maxCarriableWeight
            courierDeliveryTime.maxVehicleSpeed = maxVehicleSpeed
            courierDeliveryTime.packageList = packageList
            courierDeliveryTime.maxVehicleSpeed = maxVehicleSpeed
        }
    }


}