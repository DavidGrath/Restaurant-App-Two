package com.example.restaurantapptwo.application

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable


interface CartManager {
    fun addItem(item : CartItem) : Unit
    fun clearCart() : Unit
    fun getItems() : Observable<List<CartItem>>
    fun getItemCount() : Observable<Int>
}