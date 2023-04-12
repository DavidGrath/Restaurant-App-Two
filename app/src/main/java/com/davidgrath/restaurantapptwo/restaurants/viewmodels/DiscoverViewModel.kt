package com.davidgrath.restaurantapptwo.restaurants.viewmodels

import androidx.lifecycle.ViewModel
import com.davidgrath.restaurantapptwo.location.LocationUseCase
import com.davidgrath.restaurantapptwo.location.entities.data.SimpleLocation

class DiscoverViewModel(private val locationUseCase: LocationUseCase) : ViewModel() {

    fun getUserLocation() : SimpleLocation {
        return locationUseCase.getLocation()
    }

    fun getAllLocations() : List<SimpleLocation> {
        return locationUseCase.getAllLocations()
    }
}