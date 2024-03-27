package com.courier.service.view

import com.courier.service.model.InputData
import com.courier.service.model.Package
import com.courier.service.repository.CourierDeliveryRepository

class CourierDeliveryViewModel(private val repository: CourierDeliveryRepository) {

    val inputData = InputData()
    var view: View? = null
        set(value) {
            field = value
        }

    fun calculateDeliveryTime() {
        val packageList = repository.calculateCourierDeliveryTime(inputData)
        view?.showOutput(packageList)
    }

    interface View {
        fun showOutput(packageList: MutableList<Package>)
    }

}