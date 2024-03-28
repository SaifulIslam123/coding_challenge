package com.courier.service.repository

import com.courier.service.model.InputData
import com.courier.service.model.Package
import com.courier.service.model.Vehicle
import junit.framework.TestCase

class CourierDeliveryRepositoryTest : TestCase() {

    lateinit var repository: CourierDeliveryRepository

    fun setupDi() {

        //Creating dependencies
        val offerService = OfferService().also { it.createOffer() }
        val courierDeliveryCost = DeliveryCostService(offerService)
        val courierDeliveryTime = DeliveryTimeService(courierDeliveryCost)
        repository = CourierDeliveryRepository(courierDeliveryCost, courierDeliveryTime)

    }

    fun testCalculateCourierDeliveryTime() {
        setupDi()

        val inputData = InputData()
        inputData.baseDeliveryCost = 100
        inputData.packageList.add(Package(id = 0, packageName = "PKG1", weight = 50, distance = 30, offerCode = "OFR001"))
        inputData.packageList.add(Package(id = 1, packageName = "PKG2", weight = 75, distance = 125, offerCode = "OFR008"))
        inputData.packageList.add(Package(id = 2, packageName = "PKG3", weight = 175, distance = 100, offerCode = "OFR003"))
        inputData.packageList.add(Package(id = 3, packageName = "PKG4", weight = 110, distance = 60, offerCode = "OFR002"))
        inputData.packageList.add(Package(id = 4, packageName = "PKG5", weight = 155, distance = 95, offerCode = "NA"))
        inputData.vehicleList.add(Vehicle(0))
        inputData.vehicleList.add(Vehicle(1))
        inputData.maxVehicleSpeed = 70
        inputData.maxCarriableWeight = 200

        val outputList = repository.calculateCourierDeliveryTime(inputData)
        println(outputList)

        //check package 1 delivery cost and time
        assertEquals(4.01, outputList[0].deliveryTime)
        assertEquals(750, outputList[0].deliveryCost)

    }


}