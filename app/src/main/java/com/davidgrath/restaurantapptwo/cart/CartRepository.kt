package com.davidgrath.restaurantapptwo.cart

import com.davidgrath.restaurantapptwo.cart.entities.data.CardDetailsData
import com.davidgrath.restaurantapptwo.cart.entities.ui.CartItem
import com.davidgrath.restaurantapptwo.main.AsyncResult
import com.davidgrath.restaurantapptwo.main.NetworkClient
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class CartRepository(private val networkClient: NetworkClient) {

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

    fun addItem(item: CartItem) {
        cartItems.add(item)
        cartItemsBehaviorSubject.onNext(cartItems)
    }

    fun clearCart() {
        cartItems.clear()
        cartItemsBehaviorSubject.onNext(cartItems)
    }

    fun getItems(): Observable<List<CartItem>> {
        return cartItemsBehaviorSubject
    }

    fun getItemCount(): Observable<Int> {
        return cartItemsBehaviorSubject.map { list ->
            list.size
        }
    }

    fun checkout(amount : Float, cardDetailsData: CardDetailsData) : Observable<AsyncResult<Any>> {
        //TODO Implement
        val cardExistsInUserData = false
        val behaviorSubject : BehaviorSubject<AsyncResult<Any>> =
            BehaviorSubject.createDefault(AsyncResult.Processing())
        if(cardExistsInUserData) {
            val token = "abcd"
            networkClient.chargeCard(amount, token)
                .subscribe(
                    {
                        behaviorSubject.onNext(AsyncResult.Success(""))
                        behaviorSubject.onComplete()
                    },
                    {
                        behaviorSubject.onNext(AsyncResult.Failure(it.message))
                    }
                )
        } else {
            networkClient.tokenizeCard(cardDetailsData.cardHolderName, cardDetailsData.cardNumber,
                cardDetailsData.expiryDate, cardDetailsData.cvv)
                .flatMap { s ->
                    networkClient.chargeCard(amount, s)
                }
                .subscribe(
                    {
                        behaviorSubject.onNext(AsyncResult.Success(""))
                        behaviorSubject.onComplete()},
                    {
                        behaviorSubject.onNext(AsyncResult.Failure(it.message))
                    }
                )
        }
        return behaviorSubject
    }

}