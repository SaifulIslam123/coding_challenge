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

    private fun isInputValid(value: String): Boolean {

        try {
            if (value.isEmpty())
                return false
            if (value.toInt() == 0)
                return false
            return true
        } catch (e: Exception) {
            return false
        }
        return true
    }

    fun validatePackageInput(list: List<String>): Boolean {

        if (list.size != 2) {
            view?.showInvalidPackageError()
            return false
        } else {
            list.forEach {
                if (!isInputValid(it)) {
                    view?.showEmptyPackageError()
                    return false
                }
            }
        }
        return true
    }

    fun validatePackageDetail(parts: List<String>): Boolean {
        if (parts.size != 4) {
            view?.showPackageDetailError()
            return false
        } else {
            if (parts[0].isEmpty()) {
                view?.showPackageDetailError()
                return false
            }

            if (!isInputValid(parts[1]) || !isInputValid(parts[2])) {
                view?.showPackageDetailError()
                return false
            }

        }
        return true
    }

    fun validateVehicleDetail(parts: List<String>): Boolean {
        if (parts.size != 3) {
            view?.showVehicleDetailError()
            return false
        } else {
            parts.forEach {
                if (!isInputValid(it)) {
                    view?.showVehicleDetailError()
                    return false
                }
            }
        }
        return true
    }

    fun validateMaxVehicleWeightWithPackage(packageList: MutableList<Package>, maxWeight: Int): Boolean {

        packageList.forEach {
            if (it.weight > maxWeight) {
                view?.showVehicleWeightError()
                return false
            }
        }

        return true
    }

    interface View {
        fun showOutput(packageList: MutableList<Package>)
        fun showEmptyPackageError()
        fun showInvalidPackageError()
        fun showPackageDetailError()
        fun showVehicleDetailError()
        fun showVehicleWeightError()
    }

}