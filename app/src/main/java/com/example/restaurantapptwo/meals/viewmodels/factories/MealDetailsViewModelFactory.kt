package com.example.restaurantapptwo.meals.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.restaurantapptwo.meals.viewmodels.MealDetailsViewModel
import com.example.restaurantapptwo.restaurants.RestaurantUseCase

class MealDetailsViewModelFactory(private val useCase: RestaurantUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MealDetailsViewModel(useCase) as T
    }
}