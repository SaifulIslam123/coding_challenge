package com.courier.service.view

import com.courier.service.model.Package
import com.courier.service.model.Vehicle
import java.lang.Exception

class CourierDelivery(private val viewModel: CourierDeliveryViewModel) : CourierDeliveryViewModel.View {

    init {
        viewModel.view = this
    }

    fun getCourierDeliveryInput() {
        println("ENTER BASE_DELIVERY_COST NO_OF_PACKAGES")
        val input = readLine()
        if (input != null && input.contains(" ")) {
            val parts = input.split(" ")
            if (!viewModel.validatePackageInput(parts)) return
            try {
                viewModel.inputData.baseDeliveryCost = parts[0].toInt()
                val numberOfPackage = parts[1].toInt()

                println("ENTER PACKAGE_ID PKG_WEIGHT_IN_KG DISTANCE_IN_KM OFFER_CODE")
                repeat(numberOfPackage) {
                    val line = readLine() ?: ""
                    val parts = line.split(" ")
                    if (!viewModel.validatePackageDetail(parts)) return
                    val packageName = parts[0]
                    val weight = parts[1].toInt()
                    val distance = parts[2].toInt()
                    val offer = parts[3]
                    viewModel.inputData.packageList.add(Package(id = it, packageName = packageName, weight = weight, distance = distance, offerCode = offer))
                }

                println("ENTER NO_OF_VEHICLE MAX_SPEED MAX_CARRIABLE_WEIGHT")
                val vehicleInput = readLine()
                if (vehicleInput != null && vehicleInput.contains(" ")) {
                    val inputParts = vehicleInput.split(" ")
                    if (!viewModel.validateVehicleDetail(inputParts)) return
                    viewModel.inputData.maxVehicleSpeed = inputParts[1].toInt()
                    viewModel.inputData.maxCarriableWeight = inputParts[2].toInt()

                    repeat(inputParts[0].toInt()) {
                        viewModel.inputData.vehicleList.add(Vehicle(it))
                    }
                }
                if (!viewModel.validateMaxVehicleWeightWithPackage(viewModel.inputData.packageList,
                                viewModel.inputData.maxCarriableWeight)) return

                viewModel.calculateDeliveryTime()
            } catch (e: Exception) {
                println("Invalid input format. Please enter numbers where required.")
            }
        } else {
            println("Invalid input format.")
        }
    }


    override fun showEmptyPackageError() {
        println("BASE_DELIVERY_COST and NO_OF_PACKAGES can't be zero or empty")
    }

    override fun showInvalidPackageError() {
        println("Invalid input format. Enter BASE_DELIVERY_COST and NO_OF_PACKAGES separated by a space")
    }

    override fun showOutput(packageList: MutableList<Package>) {
        println()
        println("OUTPUT -> PACKAGE_ID DISCOUNT TOTAL_COST ESTIMATED_DELIVERY_TIME_IN_HOURS")
        println()
        packageList.forEach {
            println("${it.packageName} ${it.discount} ${it.deliveryCost} ${it.deliveryTime}")
        }
    }

    override fun showPackageDetailError() {
        println("Invalid input format for package details. Each package all property value can't be zero or empty")
    }

    override fun showVehicleDetailError() {
        println("Invalid input format for vehicle details. Vehicle all property value can't be zero or empty")
    }

    override fun showVehicleWeightError() {
        println("vehicle max carriable weight must be larger than each package item weight")
    }

    /* fun setTestData() {
        courierDeliveryCost.baseDeliveryCost = 100

        courierDeliveryTime.packageList.add(Package(id = 0, packageName = "PKG1", weight = 50, distance = 30, offerCode = "OFR001"))
        courierDeliveryTime.packageList.add(Package(id = 1, packageName = "PKG2", weight = 75, distance = 125, offerCode = "OFR008"))
        courierDeliveryTime.packageList.add(Package(id = 2, packageName = "PKG3", weight = 175, distance = 100, offerCode = "OFR003"))
        courierDeliveryTime.packageList.add(Package(id = 3, packageName = "PKG4", weight = 110, distance = 60, offerCode = "OFR002"))
        courierDeliveryTime.packageList.add(Package(id = 4, packageName = "PKG5", weight = 155, distance = 95, offerCode = "NA"))

        courierDeliveryTime.vehicleList.add(Vehicle(0))
        //courierDeliveryTime.vehicleList.add(Vehicle(1))
        // courierDeliveryTime.vehicleList.add(Vehicle(2))
        //courierDeliveryTime.vehicleList.add(Vehicle(3))

        courierDeliveryTime.maxVehicleSpeed = 70
        courierDeliveryTime.maxCarriableWeight = 200
    }*/

}

