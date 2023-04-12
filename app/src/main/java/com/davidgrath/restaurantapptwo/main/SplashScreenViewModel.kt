package com.davidgrath.restaurantapptwo.main

import androidx.lifecycle.ViewModel
import com.davidgrath.restaurantapptwo.auth.AuthUseCase

class SplashScreenViewModel(private val authUseCase: AuthUseCase) : ViewModel() {
    fun isLoggedIn() : Boolean {
        return authUseCase.authTokenExists()
    }

    fun isLocationSet() : Boolean {
        return authUseCase.isLocationSet()
    }
}