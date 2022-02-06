package com.example.restaurantapptwo.restaurants.entities

import com.example.restaurantapptwo.meals.entities.MealDetailsData

data class RestaurantDetailsData(
    val id : Int,
    val name : String,
    val address : String,
    val openTime : Int,
    val averageRating : Float,
    val reviewCount : Int,
    val deliveryCost : Int,
    //TODO is there a better way?
    var bookmarked : Boolean,
    val featuredItems : Array<MealDetailsData>,
    val menu : Map<String, Int>,
    val reviews : Array<ReviewData>
)
