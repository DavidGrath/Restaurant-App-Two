package com.davidgrath.restaurantapptwo.location

import com.davidgrath.restaurantapptwo.location.entities.data.SimpleLocation

interface LocationHelper {
    fun getSupportedLocations() : List<SimpleLocation>
}