package com.davidgrath.restaurantapptwo.meals.viewmodels

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.davidgrath.restaurantapptwo.cart.entities.ui.CartItem
import com.davidgrath.restaurantapptwo.cart.entities.ui.CartItemUI
import com.davidgrath.restaurantapptwo.meals.MealUseCase
import io.reactivex.rxjava3.core.BackpressureStrategy

class MealOrderViewModel(private val useCase: MealUseCase) : ViewModel() {

    fun getCartItems() : LiveData<List<CartItemUI>>{
        val liveData = LiveDataReactiveStreams.fromPublisher(useCase.getCartItems().toFlowable(BackpressureStrategy.BUFFER))
        val mapped = Transformations.map(liveData, object : Function<List<CartItem>, List<CartItemUI>> {

            override fun apply(input: List<CartItem>?): List<CartItemUI> {
                if(input == null) {
                    return emptyList()
                } else {
                    return input.map {
                        CartItemUI(it.name, it.description, it.price, it.imageURL)
                    }
                }
            }
        })
        return mapped
    }
}