package com.davidgrath.restaurantapptwo.cart.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.davidgrath.restaurantapptwo.cart.CartUseCase
import com.davidgrath.restaurantapptwo.cart.viewmodels.CartViewModel

class CartViewModelFactory(private val cartUseCase: CartUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CartViewModel(cartUseCase) as T
    }

}