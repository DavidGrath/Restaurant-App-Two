package com.davidgrath.restaurantapptwo.auth.entities.data

//TODO All to be securely stored using the KeyStore APIs
data class UserData(
    val publicData : UserDataPublic,
    val privateData : UserDataPrivate
) {
}