package com.example.restaurantapptwo.restaurants.entities

data class ReviewUI(
    val username : String,
    val userImageUrl : String?,
    val rating : Int,
    val reviewText : String,
    val relativeTime : String
)