package com.davidgrath.restaurantapptwo.cart.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.davidgrath.restaurantapptwo.cart.CartUseCase
import com.davidgrath.restaurantapptwo.cart.entities.data.CardDetailsData
import com.davidgrath.restaurantapptwo.cart.entities.ui.CheckoutFormUI
import com.davidgrath.restaurantapptwo.main.AsyncResult
import io.reactivex.rxjava3.core.BackpressureStrategy

class CheckoutViewModel(private val useCase: CartUseCase) : ViewModel() {

    val checkoutFormUI = CheckoutFormUI()
    fun checkout() : LiveData<AsyncResult<Any>> {
        val observable = useCase.checkout(3_000F,
            CardDetailsData(checkoutFormUI.cardNumber, checkoutFormUI.cardHolderName, checkoutFormUI.expiryDate,
                checkoutFormUI.cvv)
        )
        return LiveDataReactiveStreams.fromPublisher(observable.toFlowable(BackpressureStrategy.BUFFER))
    }
}