package com.davidgrath.restaurantapptwo.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.davidgrath.restaurantapptwo.meals.MealUseCase
import com.davidgrath.restaurantapptwo.meals.entities.MealSummaryUI
import com.davidgrath.restaurantapptwo.restaurants.RestaurantUseCase
import com.davidgrath.restaurantapptwo.restaurants.entities.RestaurantSummaryUI
import io.reactivex.rxjava3.core.BackpressureStrategy
import kotlin.collections.HashMap
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableMap
import kotlin.collections.map
import kotlin.collections.set
import kotlin.collections.toMutableMap

class HomeViewModel(
    private val mealUseCase: MealUseCase,
    private val restaurantUseCase: RestaurantUseCase
) : ViewModel() {

    val searchResults: LiveData<AsyncResult<List<RestaurantSummaryUI>>>
    private var options: MutableMap<String, String?>? = HashMap<String, String?>()

    init {
        val observable = restaurantUseCase.searchRestaurants("", null)
        val liveData =
            LiveDataReactiveStreams.fromPublisher(observable.toFlowable(BackpressureStrategy.BUFFER))
        searchResults = Transformations.map(liveData) { result ->
            val mappedResult : AsyncResult<List<RestaurantSummaryUI>> =
            when (result) {
                is AsyncResult.Failure -> {
                    AsyncResult.Failure(result.errorMessage)
                }
                is AsyncResult.Processing -> {
                    AsyncResult.Processing()
                }
                is AsyncResult.Success -> {
                    val newList = result.data.map { restaurant ->
                        com.davidgrath.restaurantapptwo.ConverterUtils.restaurantDetailsDataToRestaurantSummaryUI(restaurant)
                    }
                    AsyncResult.Success(newList)
                }
            }
            mappedResult
        }
        options?.let {
            it["sortBy"] = (null as String?)
            it["openNow"] = (null as String?)
            it["creditCards"] = (null as String?)
            it["alcoholServed"] = (null as String?)
            it["minPrice"] = (null as String?)
            it["maxPrice"] = (null as String?)
        }
    }

    fun getTrendingMeals(): LiveData<AsyncResult<List<MealSummaryUI>>> {
        val liveData =
            LiveDataReactiveStreams.fromPublisher(mealUseCase.getTrendingMeals().toFlowable(BackpressureStrategy.BUFFER))
        val mapped = Transformations.map(liveData) { result ->
            val mappedResult : AsyncResult<List<MealSummaryUI>> =
                when(result) {
                    is AsyncResult.Failure -> {
                        AsyncResult.Failure(result.errorMessage)
                    }
                    is AsyncResult.Processing -> {
                        AsyncResult.Processing()
                    }
                    is AsyncResult.Success -> {
                        val mappedList = result.data.map {
                            val price = "\u20a6${it.price}.00"
                            MealSummaryUI(
                                it.name,
                                price,
                                it.restaurantId,
                                it.restaurantName,
                                it.restaurantRating,
                                it.bookmarked,
                                it.imageUrl
                            )
                        }
                        AsyncResult.Success(mappedList)
                    }
                }
            mappedResult
        }
        return mapped
    }

    fun getMostPopularRestaurant(): LiveData<AsyncResult<RestaurantSummaryUI>> {
        val liveData =
            LiveDataReactiveStreams.fromPublisher(restaurantUseCase.getMostPopularRestaurant().toFlowable(BackpressureStrategy.BUFFER))
        val mapped = Transformations.map(liveData) { result ->
            val mappedResult : AsyncResult<RestaurantSummaryUI> =
            when(result) {
                is AsyncResult.Failure -> {
                    AsyncResult.Failure(result.errorMessage)
                }
                is AsyncResult.Processing -> {
                    AsyncResult.Processing()
                }
                is AsyncResult.Success -> {
                    val restaurantSummaryUI = com.davidgrath.restaurantapptwo.ConverterUtils.restaurantDetailsDataToRestaurantSummaryUI(result.data!!)
                    AsyncResult.Success(restaurantSummaryUI)
                }
            }
            mappedResult
        }
        return mapped
    }

    fun getLatestFeed(): LiveData<AsyncResult<List<RestaurantSummaryUI>>> {
        val liveData =
            LiveDataReactiveStreams.fromPublisher(restaurantUseCase.getLatestFeedRestaurants().toFlowable(BackpressureStrategy.BUFFER))
        val mapped = Transformations.map(liveData) { result ->
            val mappedResult : AsyncResult<List<RestaurantSummaryUI>> =
                when(result) {
                    is AsyncResult.Failure -> {
                        AsyncResult.Failure(result.errorMessage)
                    }
                    is AsyncResult.Processing -> {
                        AsyncResult.Processing()
                    }
                    is AsyncResult.Success -> {
                        val list = result.data.map {
                            com.davidgrath.restaurantapptwo.ConverterUtils.restaurantDetailsDataToRestaurantSummaryUI(it)
                        }
                        AsyncResult.Success(list)
                    }
                }
            mappedResult
        }
        return mapped
    }

    private var query = ""
    fun search(query: String) {
        this.query = query
        restaurantUseCase.searchRestaurants(this.query, this.options)
    }

    fun setSearchSortFilterOptions(options: Map<String, String?>?) {
        this.options = options?.toMutableMap()
        search(query)
    }

    fun getSortFilterOptions(): Map<String, String?>? {
        return options
    }

    fun clearSearch() {
        restaurantUseCase.searchRestaurants("", null)
    }

}