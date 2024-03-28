package com.courier.service.repository

import com.courier.service.model.InputData
import com.courier.service.model.Package

class CourierDeliveryRepository(
        private val deliveryCostService: DeliveryCostService,
        private val deliveryTimeService: DeliveryTimeService
) {

    fun calculateCourierDeliveryTime(inputData: InputData): MutableList<Package> {

        with(inputData) {
            deliveryCostService.baseDeliveryCost = baseDeliveryCost
            deliveryTimeService.maxCarriableWeight = maxCarriableWeight
            deliveryTimeService.maxVehicleSpeed = maxVehicleSpeed
            deliveryTimeService.packageList = packageList
            deliveryTimeService.vehicleList = vehicleList
        }
        return deliveryTimeService.calculateDeliveryTime()

    }


}