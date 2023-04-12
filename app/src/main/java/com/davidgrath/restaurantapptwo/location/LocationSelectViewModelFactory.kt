package com.davidgrath.restaurantapptwo.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.davidgrath.restaurantapptwo.auth.AuthUseCase

class LocationSelectViewModelFactory(private val locationHelper: LocationHelper, private val useCase: AuthUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LocationSelectViewModel(locationHelper, useCase) as T
    }
}