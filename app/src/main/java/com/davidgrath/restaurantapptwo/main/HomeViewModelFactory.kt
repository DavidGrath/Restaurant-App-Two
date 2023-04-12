package com.davidgrath.restaurantapptwo.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.davidgrath.restaurantapptwo.meals.MealUseCase
import com.davidgrath.restaurantapptwo.restaurants.RestaurantUseCase

class HomeViewModelFactory(private val mealUseCase: MealUseCase, private val restaurantUseCase: RestaurantUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(mealUseCase, restaurantUseCase) as T
    }
}