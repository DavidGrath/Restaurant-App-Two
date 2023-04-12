package com.davidgrath.restaurantapptwo.meals.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.davidgrath.restaurantapptwo.meals.viewmodels.MealDetailsViewModel
import com.davidgrath.restaurantapptwo.restaurants.RestaurantUseCase

class MealDetailsViewModelFactory(private val useCase: RestaurantUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MealDetailsViewModel(useCase) as T
    }
}