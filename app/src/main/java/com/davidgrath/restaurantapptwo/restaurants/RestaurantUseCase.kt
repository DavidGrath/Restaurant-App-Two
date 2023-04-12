package com.davidgrath.restaurantapptwo.restaurants

import com.davidgrath.restaurantapptwo.main.AsyncResult
import com.davidgrath.restaurantapptwo.main.UseCase
import com.davidgrath.restaurantapptwo.restaurants.data.RestaurantRepository
import com.davidgrath.restaurantapptwo.restaurants.entities.RestaurantDetailsData
import io.reactivex.rxjava3.core.Observable

class RestaurantUseCase(private val repository: RestaurantRepository) : UseCase {

    fun getLatestFeedRestaurants() : Observable<AsyncResult<List<RestaurantDetailsData>>> {
        return repository.getLatestFeed()
    }

    fun getMostPopularRestaurants() : Observable<AsyncResult<List<RestaurantDetailsData>>> {
        return repository.getMostPopularRestaurants()
    }

    fun getMostPopularRestaurant() : Observable<AsyncResult<RestaurantDetailsData?>> {
        return repository.getMostPopularRestaurant()
    }

    fun getRestaurantDetails(id : Int) : Observable<AsyncResult<RestaurantDetailsData?>> {
        return repository.getRestaurantDetails(id)
    }

    fun searchRestaurants(query : String, options : Map<String, String?>?) : Observable<AsyncResult<List<RestaurantDetailsData>>> {
        return repository.searchRestaurants(query, options)
    }

    fun bookmarkRestaurant(id : Int) {
        repository.addBookmark(id)
    }

    fun removeRestaurantBookmark(id : Int) {
        repository.removeBookmark(id)
    }
}