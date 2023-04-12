package com.davidgrath.restaurantapptwo.auth.entities.network

data class UserDataNetwork(
    val publicData : UserDataNetworkPublic,
    val privateData : UserDataNetworkPrivate
)