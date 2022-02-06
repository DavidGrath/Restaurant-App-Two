package com.example.restaurantapptwo.application

import android.app.Application
import com.example.restaurantapptwo.main.HomeUseCase
import com.example.restaurantapptwo.meals.data.MealRepository
import com.example.restaurantapptwo.restaurants.RestaurantUseCase
import com.example.restaurantapptwo.restaurants.data.RestaurantRepository

class RestaurantAppTwo : Application() {

    val cartManagerInstance = CartManagerTempImpl()
    fun getCartManager() : CartManager {
        return cartManagerInstance
    }
    val restaurantRepository = RestaurantRepository()
    val mealRepository = MealRepository()
    fun getRestaurantUseCase() : RestaurantUseCase {
        return RestaurantUseCase(restaurantRepository)
    }
    fun getHomeUseCase() : HomeUseCase {
        return HomeUseCase(restaurantRepository, mealRepository)
    }
}