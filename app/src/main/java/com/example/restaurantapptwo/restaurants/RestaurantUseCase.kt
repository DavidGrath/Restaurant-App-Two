package com.example.restaurantapptwo.restaurants

import com.example.restaurantapptwo.main.UseCase
import com.example.restaurantapptwo.restaurants.data.RestaurantRepository
import com.example.restaurantapptwo.restaurants.entities.RestaurantDetailsData

class RestaurantUseCase(private val repository: RestaurantRepository) : UseCase {

    fun getLatestFeedRestaurants() : List<RestaurantDetailsData> {
        return repository.getLatestFeed()
    }

    fun getMostPopularRestaurants() : List<RestaurantDetailsData> {
        return repository.getMostPopularRestaurants()
    }

    fun getRestaurantDetails(id : Int) : RestaurantDetailsData? {
        return repository.getRestaurantDetails(id)
    }

    fun bookmarkRestaurant(id : Int) {
        repository.addBookmark(id)
    }

    fun removeRestaurantBookmark(id : Int) {
        repository.removeBookmark(id)
    }
}