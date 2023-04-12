package com.davidgrath.restaurantapptwo.meals.entities

data class MealSummaryUI(
    val name : String,
    val price : String,
    val restaurantId : Int,
    val restaurantName : String,
    //TODO I'm a bit confused about this field, but that's what I was able
    // to derive from the mockup
    val restaurantRating : Float,
    val bookmarked : Boolean,
    val imageUrl : String?
)