package com.davidgrath.restaurantapptwo.location

import com.davidgrath.restaurantapptwo.auth.AuthRepository
import com.davidgrath.restaurantapptwo.location.entities.data.SimpleLocation
import com.davidgrath.restaurantapptwo.main.UseCase

class LocationUseCase(private val authRepository: AuthRepository, private val locationHelper: LocationHelper) :
    UseCase {
    fun getLocation() : SimpleLocation {
        return SimpleLocation.fromString(authRepository.getLocation())
    }

    fun getAllLocations() : List<SimpleLocation> {
        return locationHelper.getSupportedLocations()
    }
}