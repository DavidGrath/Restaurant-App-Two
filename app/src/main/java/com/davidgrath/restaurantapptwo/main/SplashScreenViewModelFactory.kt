package com.davidgrath.restaurantapptwo.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.davidgrath.restaurantapptwo.auth.AuthUseCase

class SplashScreenViewModelFactory(private val useCase : AuthUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SplashScreenViewModel(useCase) as T
    }

}