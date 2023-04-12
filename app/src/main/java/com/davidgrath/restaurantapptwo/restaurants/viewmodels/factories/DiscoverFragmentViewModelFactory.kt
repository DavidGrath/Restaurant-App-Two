package com.davidgrath.restaurantapptwo.restaurants.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.davidgrath.restaurantapptwo.location.LocationUseCase
import com.davidgrath.restaurantapptwo.restaurants.viewmodels.DiscoverViewModel

class DiscoverFragmentViewModelFactory(private val useCase: LocationUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DiscoverViewModel(useCase) as T
    }
}