package com.davidgrath.restaurantapptwo.meals.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.davidgrath.restaurantapptwo.meals.MealUseCase
import com.davidgrath.restaurantapptwo.meals.viewmodels.TrendingViewModel

class TrendingViewModelFactory(private val mealUseCase: MealUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TrendingViewModel(mealUseCase) as T
    }

}