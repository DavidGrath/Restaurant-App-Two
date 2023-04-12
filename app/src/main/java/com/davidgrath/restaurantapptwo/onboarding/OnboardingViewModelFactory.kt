package com.davidgrath.restaurantapptwo.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.davidgrath.restaurantapptwo.auth.AuthUseCase

class OnboardingViewModelFactory(private val useCase : AuthUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OnboardingViewModel(useCase) as T
    }
}