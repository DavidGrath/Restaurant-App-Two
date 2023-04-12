package com.davidgrath.restaurantapptwo.onboarding

import androidx.lifecycle.ViewModel
import com.davidgrath.restaurantapptwo.auth.AuthUseCase

class OnboardingViewModel(val useCase: AuthUseCase) : ViewModel() {

    fun isLoggedIn() : Boolean {
        return useCase.authTokenExists()
    }

    fun isLocationSet() : Boolean {
        return useCase.isLocationSet()
    }
}