package com.davidgrath.restaurantapptwo.meals.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.davidgrath.restaurantapptwo.meals.MealUseCase
import com.davidgrath.restaurantapptwo.meals.viewmodels.MealOrderViewModel

class MealOrderViewModelFactory(private val useCase: MealUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MealOrderViewModel(useCase) as T
    }
}