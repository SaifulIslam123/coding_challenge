package com.courier.service

import com.courier.service.model.InputData
import com.courier.service.repository.CourierDeliveryRepository

class ViewModel(private val repository: CourierDeliveryRepository) {

    val inputData = InputData()

    fun calculateDeliveryTime() {
        repository.calculateCourierDeliveryTime()
    }

}