package com.davidgrath.restaurantapptwo.auth.entities.network

data class UserDataNetworkPrivate(
    val authToken : String,
    val bookmarks : List<String>,
    val creditCards : List<String>,
    val deliveryAddresses : List<String>,
    val location : String? = null
) {
}