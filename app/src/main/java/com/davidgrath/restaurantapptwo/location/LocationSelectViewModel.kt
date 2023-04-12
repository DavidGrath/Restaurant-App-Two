package com.davidgrath.restaurantapptwo.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davidgrath.restaurantapptwo.auth.AuthUseCase
import com.davidgrath.restaurantapptwo.location.entities.data.SimpleLocation

class LocationSelectViewModel(private val locationHelper: LocationHelper, private val useCase: AuthUseCase) : ViewModel() {

    private val locationList = locationHelper.getSupportedLocations()

    private val _locationListLiveData = MutableLiveData<List<SimpleLocation>>()
    val locationListLiveData : LiveData<List<SimpleLocation>> = _locationListLiveData

    init {
        _locationListLiveData.postValue(locationList)
    }

    fun searchLocations(query : String) {
        val filtered = locationList.filter {
            it.name.contains(query)
        }
        _locationListLiveData.postValue(filtered)
    }

    fun clearSearch() {
        _locationListLiveData.postValue(locationList)
    }
    fun setLocation(location : String) {
        useCase.setLocation(location)
    }

    fun getDefaultLocation() : SimpleLocation {
        val location = locationList.find { it.name.equals("Ikeja", true) }!!
        return location
    }
}