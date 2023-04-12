package com.davidgrath.restaurantapptwo.meals

import com.davidgrath.restaurantapptwo.cart.CartRepository
import com.davidgrath.restaurantapptwo.cart.entities.ui.CartItem
import com.davidgrath.restaurantapptwo.main.AsyncResult
import com.davidgrath.restaurantapptwo.main.UseCase
import com.davidgrath.restaurantapptwo.meals.entities.MealDetailsData
import com.davidgrath.restaurantapptwo.restaurants.data.RestaurantRepository
import io.reactivex.rxjava3.core.Observable

class MealUseCase(private val repository: RestaurantRepository, private val cartRepository: CartRepository) : UseCase {

    fun getCartItems() : Observable<List<CartItem>>{
        return cartRepository.getItems()
    }

    fun getTrendingMeals() : Observable<AsyncResult<List<MealDetailsData>>> {
        return repository.getTrendingMeals()
    }
}