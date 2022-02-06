package com.example.restaurantapptwo.main

import com.example.restaurantapptwo.meals.data.MealRepository
import com.example.restaurantapptwo.meals.entities.MealDetailsData
import com.example.restaurantapptwo.restaurants.data.RestaurantRepository
import com.example.restaurantapptwo.restaurants.entities.RestaurantDetailsData

//TODO May remove this class because I feel it's not a proper use case, and replace it with the restaurant and meal use cases
class HomeUseCase(private val restaurantRepository: RestaurantRepository, private val mealRepository: MealRepository) : UseCase {
    fun getTrendingMeals() : List<MealDetailsData> {
        return emptyList()
//        return mealRepository.getTrendingMeals()
    }

    fun getMostPopularRestaurant() : RestaurantDetailsData? {
        val list = restaurantRepository.getMostPopularRestaurants()
        return if(list.isEmpty()) {
            null
        } else {
            list.first()
        }
    }

    fun getLatestFeed() : List<RestaurantDetailsData> {
        return restaurantRepository.getLatestFeed()
    }

    //TODO is there a data type or interface-based pattern that fits all filters?
    fun searchRestaurants(query : String, filters : List<String>) : List<RestaurantDetailsData> {
        return restaurantRepository.searchRestaurants(query)
    }
}