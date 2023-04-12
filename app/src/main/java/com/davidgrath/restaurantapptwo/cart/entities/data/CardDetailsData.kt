package com.davidgrath.restaurantapptwo.cart.entities.data

//TODO Card Details as a POJO?
data class CardDetailsData(
    val cardNumber : String,
    val cardHolderName : String,
    val expiryDate : String,
    val cvv : String
) {
}