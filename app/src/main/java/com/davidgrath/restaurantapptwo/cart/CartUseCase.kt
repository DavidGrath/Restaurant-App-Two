package com.davidgrath.restaurantapptwo.cart

import com.davidgrath.restaurantapptwo.cart.entities.data.CardDetailsData
import com.davidgrath.restaurantapptwo.cart.entities.ui.CartItem
import com.davidgrath.restaurantapptwo.main.AsyncResult
import com.davidgrath.restaurantapptwo.main.UseCase
import io.reactivex.rxjava3.core.Observable

class CartUseCase(private val repository: CartRepository) : UseCase {

    fun addItem(item: CartItem) {
        repository.addItem(item)
    }

    fun clearCart() {
        repository.clearCart()
    }

    fun getItems(): Observable<List<CartItem>> {
        return repository.getItems()
    }

    fun getItemCount(): Observable<Int> {
        return repository.getItemCount()
    }

    fun checkout(amount : Float, cardDetails : CardDetailsData) : Observable<AsyncResult<Any>> {
        return repository.checkout(amount, cardDetails)
    }
}