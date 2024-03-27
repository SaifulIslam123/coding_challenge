package com.courier.service

import com.courier.service.view.CourierDelivery
import com.courier.service.repository.CourierDeliveryCost
import com.courier.service.repository.CourierDeliveryTime
import com.courier.service.view.CourierDeliveryViewModel
import com.courier.service.repository.CourierDeliveryRepository
import com.courier.service.repository.OfferService


/*fun main() {

    val obj = CourierDelivery()
    obj.takeInput()

}*/
/*while (rootPackageArray.size>=0){
        1/ find all combinations
        2/ check the highest pair or single package
        3/ Remove this packages or single packages from the root total packages
        4/ find which vehicle is now available (compare all vehicles available time and pick the less value one)
        5/ calculate all deliver packages time and vehicle next available timee
        6/ save this output to map based on package object
    }*//*


}
*/

fun main() {

    //Creating dependencies
    val offerService = OfferService().also { it.createOffer() }
    val courierDeliveryCost = CourierDeliveryCost(offerService)
    val courierDeliveryTime = CourierDeliveryTime(courierDeliveryCost)
    val repository = CourierDeliveryRepository(courierDeliveryCost, courierDeliveryTime)
    val viewModel = CourierDeliveryViewModel(repository)

    CourierDelivery(viewModel).also { it.takeInput() }

}