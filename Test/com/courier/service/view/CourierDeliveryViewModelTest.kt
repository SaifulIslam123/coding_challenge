package com.courier.service.view

import com.courier.service.repository.DeliveryCostService
import com.courier.service.repository.CourierDeliveryRepository
import com.courier.service.repository.DeliveryTimeService
import com.courier.service.repository.OfferService
import junit.framework.TestCase
import org.junit.Test

class CourierDeliveryViewModelTest : TestCase() {

    lateinit var viewModel: CourierDeliveryViewModel

    fun setupDi() {

        //Creating dependencies
        val offerService = OfferService().also { it.createOffer() }
        val courierDeliveryCost = DeliveryCostService(offerService)
        val courierDeliveryTime = DeliveryTimeService(courierDeliveryCost)
        val repository = CourierDeliveryRepository(courierDeliveryCost, courierDeliveryTime)
        viewModel = CourierDeliveryViewModel(repository)
    }

    @Test
    fun testValidatePackageInput() {
        setupDi()

        assertTrue(viewModel.validatePackageInput(listOf("2", "10")))
        assertFalse(viewModel.validatePackageInput(listOf("2", "")))
        assertFalse(viewModel.validatePackageInput(listOf("2")))
    }


    @Test
    fun testValidatePackageDetail() {
        setupDi()

        assertFalse(viewModel.validatePackageDetail(listOf("pkg1", "10")))
        assertTrue(viewModel.validatePackageDetail(listOf("pkg1", "10", "100", "offer001")))
        assertFalse(viewModel.validatePackageDetail(listOf("pkg1", "0", "100", "offer001")))
        assertFalse(viewModel.validatePackageDetail(listOf("pkg1", "22", "0", "offer001")))
        assertTrue(viewModel.validatePackageDetail(listOf("pkg1", "22", "20")))
    }

    @Test
    fun testVehicleDetail() {
        setupDi()

        assertTrue(viewModel.validateVehicleDetail(listOf("10", "100", "300")))
        assertFalse(viewModel.validateVehicleDetail(listOf("10", "100")))
        assertFalse(viewModel.validateVehicleDetail(listOf("0", "40","200")))
        assertFalse(viewModel.validateVehicleDetail(listOf("10", "0","200")))
        assertFalse(viewModel.validateVehicleDetail(listOf("10", "10","0")))

    }
}