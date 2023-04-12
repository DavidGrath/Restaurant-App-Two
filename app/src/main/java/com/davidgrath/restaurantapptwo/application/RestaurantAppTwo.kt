package com.davidgrath.restaurantapptwo.application

import android.app.Application
import com.davidgrath.restaurantapptwo.Constants
import com.davidgrath.restaurantapptwo.auth.AuthRepository
import com.davidgrath.restaurantapptwo.auth.AuthStorageHelperImpl
import com.davidgrath.restaurantapptwo.auth.AuthUseCase
import com.davidgrath.restaurantapptwo.cart.CartRepository
import com.davidgrath.restaurantapptwo.cart.CartUseCase
import com.davidgrath.restaurantapptwo.location.LocationHelper
import com.davidgrath.restaurantapptwo.location.LocationHelperImpl
import com.davidgrath.restaurantapptwo.location.LocationUseCase
import com.davidgrath.restaurantapptwo.main.NetworkClient
import com.davidgrath.restaurantapptwo.main.NetworkClientTempImpl
import com.davidgrath.restaurantapptwo.meals.MealUseCase
import com.davidgrath.restaurantapptwo.restaurants.RestaurantUseCase
import com.davidgrath.restaurantapptwo.restaurants.data.RestaurantRepository

class RestaurantAppTwo : Application() {

    //TODO Dagger

    val networkClient : NetworkClient = NetworkClientTempImpl()
    val cartRepository = CartRepository(networkClient)
    lateinit var authRepository : AuthRepository
    val restaurantRepository = RestaurantRepository(networkClient)
    lateinit var locationHelper : LocationHelper

    override fun onCreate() {
        super.onCreate()
        val storageHelper = AuthStorageHelperImpl(this)
        authRepository =
            AuthRepository(networkClient, storageHelper)
        locationHelper = LocationHelperImpl(this)
        val preferences = getSharedPreferences(Constants.APPLICATION_NAME, MODE_PRIVATE)
        preferences.edit().clear().commit()
    }

    fun getCartUseCase() : CartUseCase {
        return CartUseCase(cartRepository)
    }
    fun getRestaurantUseCase() : RestaurantUseCase {
        return RestaurantUseCase(restaurantRepository)
    }

    fun getMealUseCase() : MealUseCase {
        return MealUseCase(restaurantRepository, cartRepository)
    }

    fun getAuthUseCase() : AuthUseCase {
        return AuthUseCase(authRepository)
    }

    fun getLocationUseCase() : LocationUseCase {
        return LocationUseCase(authRepository, locationHelper)
    }
}