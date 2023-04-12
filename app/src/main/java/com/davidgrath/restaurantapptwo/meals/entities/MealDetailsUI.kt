package com.davidgrath.restaurantapptwo.meals.entities

data class MealDetailsUI(
    val id : Int,
    val name : String,
    val restaurantId : Int,
    val restaurantName : String,
    val restaurantRating : Float,
    val description : String,
    val extras : Map<String, String>,
    val bookmarked : Boolean,
    val price : String,
    val imageUrl : String?
)
