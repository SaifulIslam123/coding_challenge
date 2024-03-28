package com.courier.service.view

import com.courier.service.repository.DeliveryCostService
import com.courier.service.repository.CourierDeliveryRepository
import com.courier.service.repository.DeliveryTimeService
import com.courier.service.repository.OfferService
import junit.framework.TestCase
import org.junit.Test

class CourierDeliveryViewModelTest : TestCase() {

    lateinit var viewModel: CourierDeliveryViewModel

    fun setup() {

        //Creating dependencies
        val offerService = OfferService().also { it.createOffer() }
        val courierDeliveryCost = DeliveryCostService(offerService)
        val courierDeliveryTime = DeliveryTimeService(courierDeliveryCost)
        val repository = CourierDeliveryRepository(courierDeliveryCost, courierDeliveryTime)
        viewModel = CourierDeliveryViewModel(repository)
    }

    @Test
    fun testValidatePackageInput() {
        setup()

        assertTrue(viewModel.validateVehicleDetail(listOf("2", "10", "100")))
        assertFalse(viewModel.validateVehicleDetail(listOf("2", "", "100")))
    }


    @Test
    fun testValidatePackageDetail() {
        setup()

        assertFalse(viewModel.validatePackageDetail(listOf("pkg1", "10", "100")))
        assertTrue(viewModel.validatePackageDetail(listOf("pkg1", "10", "100", "offer001")))
        assertFalse(viewModel.validatePackageDetail(listOf("pkg1", "0", "100", "offer001")))
        assertFalse(viewModel.validatePackageDetail(listOf("pkg1", "22", "0", "offer001")))
    }

    @Test
    fun testVehicleDetail() {
        setup()

        assertTrue(viewModel.validateVehicleDetail(listOf("10", "100", "300")))
        assertFalse(viewModel.validateVehicleDetail(listOf("10", "100")))
        assertFalse(viewModel.validateVehicleDetail(listOf("0", "40","200")))
        assertFalse(viewModel.validateVehicleDetail(listOf("10", "0","200")))
        assertFalse(viewModel.validateVehicleDetail(listOf("10", "10","0")))

    }
}