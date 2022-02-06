package com.example.restaurantapptwo.application

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class CartManagerTempImpl : CartManager {

    private var cartItems = arrayListOf<CartItem>()
    private val cartItemsBehaviorSubject = BehaviorSubject.create<List<CartItem>>()

    init {
        val elements = arrayOf(
            CartItem("Bread Egg Delight", "2x bread loaf, 3x egg yolk, 1x green tea", 2_000, null),
            CartItem("Suya Beef Surprise", "2x gizzard, 3x beef, 1x chicken", 10_000, null),
            CartItem("Don't", "???", 0, null),
            CartItem("Amala", "2x amala, 1x egusi, 7x beef", 500, null),
            CartItem("Indomie", "5x Indomie", 1_000, null),
            CartItem("Bread Egg Delight", "2x bread loaf, 3x egg yolk, 1x green tea", 2_000, null),
            CartItem("Suya Beef Surprise", "2x gizzard, 3x beef, 1x chicken", 10_000, null),
            CartItem("Don't", "???", 0, null),
            CartItem("Amala", "2x amala, 1x egusi, 7x beef", 500, null),
            CartItem("Indomie", "5x Indomie", 1_000, null)
        )
        cartItems.addAll(elements)
        cartItemsBehaviorSubject.onNext(cartItems)
    }

    override fun addItem(item: CartItem) {
        cartItems.add(item)
        cartItemsBehaviorSubject.onNext(cartItems)
    }

    override fun clearCart() {
        cartItems.clear()
        cartItemsBehaviorSubject.onNext(cartItems)
    }

    override fun getItems(): Observable<List<CartItem>> {
        return cartItemsBehaviorSubject
    }

    override fun getItemCount(): Observable<Int> {
        return cartItemsBehaviorSubject.map { list ->
            list.size
        }
    }
}