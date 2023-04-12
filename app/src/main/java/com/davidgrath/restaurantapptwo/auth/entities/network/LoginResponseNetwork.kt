package com.davidgrath.restaurantapptwo.auth.entities.network

data class LoginResponseNetwork(
    val token : String,
    val userData : UserDataNetwork
) {
}