package com.davidgrath.restaurantapptwo.auth.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.davidgrath.restaurantapptwo.auth.AuthUseCase
import com.davidgrath.restaurantapptwo.auth.viewmodels.CreateAccountViewModel

class CreateAccountViewModelFactory(private val authUseCase: AuthUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateAccountViewModel(authUseCase) as T
    }

}