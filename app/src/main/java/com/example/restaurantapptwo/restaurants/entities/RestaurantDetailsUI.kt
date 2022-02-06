package com.example.restaurantapptwo.restaurants.entities

import com.example.restaurantapptwo.meals.entities.MealSummaryUI

data class RestaurantDetailsUI(
    val name : String,
    val address : String,
    val openTime : Int,
    val averageRating : Float,
    val reviewCount : Int,
    val deliveryCostText : String,
    val bookmarked : Boolean,
    val featuredItems : List<MealSummaryUI>,
    val menu : Map<String, Int>,
    val reviews : List<ReviewUI>
)