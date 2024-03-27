package com.courier.service

import com.courier.service.view.CourierDelivery
import com.courier.service.repository.CourierDeliveryCost
import com.courier.service.repository.CourierDeliveryTime
import com.courier.service.view.CourierDeliveryViewModel
import com.courier.service.repository.CourierDeliveryRepository
import com.courier.service.repository.OfferService

fun main() {

    //Creating dependencies
    val offerService = OfferService().also { it.createOffer() }
    val courierDeliveryCost = CourierDeliveryCost(offerService)
    val courierDeliveryTime = CourierDeliveryTime(courierDeliveryCost)
    val repository = CourierDeliveryRepository(courierDeliveryCost, courierDeliveryTime)
    val viewModel = CourierDeliveryViewModel(repository)

    CourierDelivery(viewModel).also { it.getCourierDeliveryInput() }

}