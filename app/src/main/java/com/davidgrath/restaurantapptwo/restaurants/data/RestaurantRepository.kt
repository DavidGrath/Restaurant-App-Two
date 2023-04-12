package com.davidgrath.restaurantapptwo.restaurants.data

import com.davidgrath.restaurantapptwo.main.AsyncResult
import com.davidgrath.restaurantapptwo.main.NetworkClient
import com.davidgrath.restaurantapptwo.meals.entities.MealDetailsData
import com.davidgrath.restaurantapptwo.restaurants.entities.RestaurantDetailsData
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class RestaurantRepository(private val networkClient: NetworkClient) {

    private val restaurantBookmarks = HashSet<Int>()

    fun addBookmark(id: Int) {
        restaurantBookmarks.add(id)
    }

    fun removeBookmark(id : Int) {
        restaurantBookmarks.remove(id)
    }

    fun getMostPopularRestaurants() : Observable<AsyncResult<List<RestaurantDetailsData>>> {
        val behaviorSubject : BehaviorSubject<AsyncResult<List<RestaurantDetailsData>>> =
            BehaviorSubject.createDefault(AsyncResult.Processing())
        networkClient.getMostPopularRestaurants()
            .subscribe(
                {
                    behaviorSubject.onNext(AsyncResult.Success(it))
                },
                {
                    behaviorSubject.onNext(AsyncResult.Failure(it.message))
                }
            )
        return behaviorSubject
    }

    fun getMostPopularRestaurant() : Observable<AsyncResult<RestaurantDetailsData?>> {
        val behaviorSubject : BehaviorSubject<AsyncResult<RestaurantDetailsData?>> =
            BehaviorSubject.createDefault(AsyncResult.Processing())
        networkClient.getMostPopularRestaurants()
            .subscribe(
                {
                    behaviorSubject.onNext(AsyncResult.Success(it.first()))
                },
                {
                    behaviorSubject.onNext(AsyncResult.Failure(it.message))
                }
            )
        return behaviorSubject
    }

    fun getLatestFeed() : Observable<AsyncResult<List<RestaurantDetailsData>>> {
        val behaviorSubject : BehaviorSubject<AsyncResult<List<RestaurantDetailsData>>> =
            BehaviorSubject.createDefault(AsyncResult.Processing())
        networkClient.getLatestFeed()
            .subscribe(
                {
                    behaviorSubject.onNext(AsyncResult.Success(it))
                },
                {
                    behaviorSubject.onNext(AsyncResult.Failure(it.message))
                }
            )
        return behaviorSubject
    }

    fun getRestaurantDetails(id : Int) : Observable<AsyncResult<RestaurantDetailsData?>> {
        val behaviorSubject : BehaviorSubject<AsyncResult<RestaurantDetailsData?>> =
            BehaviorSubject.createDefault(AsyncResult.Processing())
        networkClient.getRestaurantDetails(id)
            .subscribe(
                {
                    behaviorSubject.onNext(AsyncResult.Success(it))
                },
                {
                    behaviorSubject.onNext(AsyncResult.Failure(it.message))
                }
            )
        return behaviorSubject
    }

    fun getTrendingMeals() : Observable<AsyncResult<List<MealDetailsData>>> {
        val behaviorSubject : BehaviorSubject<AsyncResult<List<MealDetailsData>>> =
            BehaviorSubject.createDefault(AsyncResult.Processing())
        networkClient.getTrendingMeals()
            .subscribe(
                {
                    behaviorSubject.onNext(AsyncResult.Success(it))
                },
                {
                    behaviorSubject.onNext(AsyncResult.Failure(it.message))
                }
            )
        return behaviorSubject
    }

    fun searchRestaurants(query : String, options : Map<String, String?>? = null) : Observable<AsyncResult<List<RestaurantDetailsData>>> {
        val behaviorSubject : BehaviorSubject<AsyncResult<List<RestaurantDetailsData>>> =
            BehaviorSubject.createDefault(AsyncResult.Processing())
        networkClient.searchRestaurants(query, options)
            .subscribe(
                {
                    behaviorSubject.onNext(AsyncResult.Success(it))
                },
                {
                    behaviorSubject.onNext(AsyncResult.Failure(it.message))
                }
            )
        return behaviorSubject
    }
}