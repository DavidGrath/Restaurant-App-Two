package com.davidgrath.restaurantapptwo.auth.entities.data

data class UserDataPrivate(
    val authToken : String,
    val bookmarks : List<String>,
    val creditCards : List<String>,
    val deliveryAddresses : List<String>,
    val location : String? = null
) {
}