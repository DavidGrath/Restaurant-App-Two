package com.example.restaurantapptwo.cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.example.restaurantapptwo.application.CartManager
import com.example.restaurantapptwo.application.RestaurantAppTwo
import io.reactivex.rxjava3.core.BackpressureStrategy

class CartViewModel(application: Application) : AndroidViewModel(application) {

    val cartManager : CartManager
    val cartItems : LiveData<List<CartItemUI>>
    init {
        cartManager = (application as RestaurantAppTwo).getCartManager()
        val itemsMapped = cartManager.getItems().map { it ->
            it.map { item -> CartItemUI(item.name, item.description, item.price, item.imageURL) }
        }
        cartItems = LiveDataReactiveStreams.fromPublisher(itemsMapped.toFlowable(BackpressureStrategy.BUFFER))
    }
}