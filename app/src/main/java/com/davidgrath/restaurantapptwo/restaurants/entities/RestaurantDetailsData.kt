package com.davidgrath.restaurantapptwo.restaurants.entities

import com.davidgrath.restaurantapptwo.meals.entities.MealDetailsData

data class RestaurantDetailsData(
    val id : Int,
    val name : String,
    val address : String,
    val openTime : Int,
    //TODO openNow is a derived attribute the way I see it, but let's leave it like this for now
    val openNow : Boolean,
    val averageRating : Float,
    val reviewCount : Int,
    val deliveryCost : Int,
    //TODO is there a better way?
    var bookmarked : Boolean,
    val featuredItems : Array<MealDetailsData>,
    val menu : Map<String, Int>,
    val reviews : Array<ReviewData>
)
