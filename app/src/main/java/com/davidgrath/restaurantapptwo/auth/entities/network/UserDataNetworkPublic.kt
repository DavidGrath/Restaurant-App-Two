package com.davidgrath.restaurantapptwo.auth.entities.network

data class UserDataNetworkPublic(
    val username : String,
    val email : String,
    val verified : Boolean,
    //TODO Apply consistency between 'Url' and 'URL' occurrences
    // all should be 'URL'
    val displayPictureURL : String? = null,
    val reviews : List<String>
) {
}