package com.davidgrath.restaurantapptwo.cart.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.davidgrath.restaurantapptwo.cart.CartUseCase
import com.davidgrath.restaurantapptwo.cart.viewmodels.CheckoutViewModel

class CheckoutViewModelFactory(private val cartUseCase: CartUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CheckoutViewModel(cartUseCase) as T
    }

}