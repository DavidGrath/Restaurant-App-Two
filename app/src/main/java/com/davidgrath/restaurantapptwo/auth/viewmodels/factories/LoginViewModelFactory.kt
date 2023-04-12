package com.davidgrath.restaurantapptwo.auth.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.davidgrath.restaurantapptwo.auth.AuthUseCase
import com.davidgrath.restaurantapptwo.auth.viewmodels.LoginViewModel

class LoginViewModelFactory(private val authUseCase: AuthUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(authUseCase) as T
    }
}