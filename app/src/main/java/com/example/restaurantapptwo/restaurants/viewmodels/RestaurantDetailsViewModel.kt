package com.example.restaurantapptwo.restaurants.viewmodels

import androidx.lifecycle.ViewModel
import com.example.restaurantapptwo.meals.entities.MealSummaryUI
import com.example.restaurantapptwo.restaurants.RestaurantUseCase
import com.example.restaurantapptwo.restaurants.entities.RestaurantDetailsData
import com.example.restaurantapptwo.restaurants.entities.RestaurantDetailsUI
import com.example.restaurantapptwo.restaurants.entities.ReviewData
import com.example.restaurantapptwo.restaurants.entities.ReviewUI

class RestaurantDetailsViewModel(val useCase: RestaurantUseCase) : ViewModel() {

    fun getRestaurantDetails(id : Int) : RestaurantDetailsUI {
        //TODO Error cases and whatnot
        val data = useCase.getRestaurantDetails(id)!!
        val deliveryCostText = if(data.deliveryCost > 0) "\u20a6${data.deliveryCost}" else "Free"
        val featuredItems = data.featuredItems.map {
            //TODO Decimal Format
            val price = "\u20a6${it.price}.00"
            MealSummaryUI(it.name, price, it.restaurantId, it.restaurantName, it.restaurantRating, it.bookmarked, it.imageUrl)
        }
        //TODO Time format
        val relativeTime = "2 hours ago"
        val reviews = data.reviews.map { ReviewUI(it.username, it.userImageUrl, it.rating, it.reviewText, relativeTime) }
        return RestaurantDetailsUI(data.name, data.address, data.openTime, data.averageRating,
        data.reviewCount, deliveryCostText, data.bookmarked, featuredItems, data.menu, reviews)
    }
}