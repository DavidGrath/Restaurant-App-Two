package com.example.restaurantapptwo.restaurants.entities

data class RestaurantSummaryUI(
    val name : String,
    val address : String,
    val openTimeText : String,
    val averageRating : Float,
    val image1Url : String?,
    val image2Url : String?,
    val image3Url : String?
)