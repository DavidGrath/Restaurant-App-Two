package com.example.restaurantapptwo.restaurants.entities

data class ReviewData(
    val username : String,
    val userImageUrl : String?,
    val rating : Int,
    val reviewText : String,
    val timestamp : Long
)