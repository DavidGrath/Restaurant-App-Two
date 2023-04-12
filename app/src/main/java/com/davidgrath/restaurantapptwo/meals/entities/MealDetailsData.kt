package com.davidgrath.restaurantapptwo.meals.entities

data class MealDetailsData(
    val id : Int,
    val name : String,
    val restaurantId : Int,
    val restaurantName : String,
    val restaurantRating : Float,
    val description : String,
    val extras : Map<String, Int>,
    val bookmarked : Boolean,
    val price : Int,
    val imageUrl : String?
)