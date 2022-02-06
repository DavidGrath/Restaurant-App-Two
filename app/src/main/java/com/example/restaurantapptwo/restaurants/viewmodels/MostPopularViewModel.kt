package com.example.restaurantapptwo.restaurants.viewmodels

import androidx.lifecycle.ViewModel
import com.example.restaurantapptwo.restaurants.RestaurantUseCase
import com.example.restaurantapptwo.restaurants.entities.RestaurantSummaryUI

class MostPopularViewModel(private val useCase: RestaurantUseCase) : ViewModel() {

    fun getMostPopularRestaurants() : List<RestaurantSummaryUI> {
        return useCase.getMostPopularRestaurants().map { it ->
            //TODO Time format
            val hour = it.openTime / 60
            val minute = it.openTime % 60
            val amPm = if(it.openTime/60 > 12) "AM" else "PM"
            val openTimeText = "Open $hour:$minute $amPm"
            RestaurantSummaryUI(it.name, it.address, openTimeText, it.averageRating, null, null, null)
        }
    }
}