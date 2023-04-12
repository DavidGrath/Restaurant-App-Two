package com.davidgrath.restaurantapptwo.cart.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.davidgrath.restaurantapptwo.cart.CartUseCase
import com.davidgrath.restaurantapptwo.cart.entities.ui.CartItemUI
import io.reactivex.rxjava3.core.BackpressureStrategy

class CartViewModel(private val useCase: CartUseCase) : ViewModel() {

    val cartItems : LiveData<List<CartItemUI>>
    init {
        val itemsMapped = useCase.getItems().map { it ->
            it.map { item -> CartItemUI(item.name, item.description, item.price, item.imageURL) }
        }
        cartItems = LiveDataReactiveStreams.fromPublisher(itemsMapped.toFlowable(BackpressureStrategy.BUFFER))
    }
}