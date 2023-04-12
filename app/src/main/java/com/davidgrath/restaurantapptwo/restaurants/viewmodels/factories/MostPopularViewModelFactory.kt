package com.davidgrath.restaurantapptwo.restaurants.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.davidgrath.restaurantapptwo.restaurants.RestaurantUseCase
import com.davidgrath.restaurantapptwo.restaurants.viewmodels.MostPopularViewModel

class MostPopularViewModelFactory(val useCase: RestaurantUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MostPopularViewModel(useCase) as T
    }
}