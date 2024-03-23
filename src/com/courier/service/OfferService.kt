package com.courier.service

class OfferService {

    private var offerList = ArrayList<Offer>()

    fun addOffer(offers: ArrayList<Offer>) {
        offerList.addAll(offers)
    }

    fun addOffer(offer: Offer) {
        offerList.add(offer)
    }

    fun removeOffer(offer: Offer) {
        offerList.remove(offer)
    }

    fun getDiscount(offerName: String, distance: Int, weight: Int): Int {
        val index = offerList.indexOfFirst { offer -> offer.offerName.equals(offerName) }
        if (index != -1) {
            val offer = offerList.get(index)
            if ((distance >= offer.distance.min && distance <= offer.distance.max) &&
                weight >= offer.weight.min && weight <= offer.weight.max
            ) {
                return offer.discountPercentage
            } else return 0
        } else {
            return 0
        }
    }
}

data class Offer(val offerName: String, val discountPercentage: Int, val distance: Distance, val weight: Weight)
data class Weight(val min: Int, val max: Int)
data class Distance(val min: Int, val max: Int)
