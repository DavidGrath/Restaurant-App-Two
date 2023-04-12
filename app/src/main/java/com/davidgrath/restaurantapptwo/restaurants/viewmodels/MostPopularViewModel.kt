package com.davidgrath.restaurantapptwo.restaurants.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.davidgrath.restaurantapptwo.main.AsyncResult
import com.davidgrath.restaurantapptwo.restaurants.RestaurantUseCase
import com.davidgrath.restaurantapptwo.restaurants.entities.RestaurantSummaryUI
import io.reactivex.rxjava3.core.BackpressureStrategy

class MostPopularViewModel(private val useCase: RestaurantUseCase) : ViewModel() {

    fun getMostPopularRestaurants(): LiveData<AsyncResult<List<RestaurantSummaryUI>>> {
        val liveData =
            LiveDataReactiveStreams.fromPublisher(
                useCase.getMostPopularRestaurants().toFlowable(
                    BackpressureStrategy.BUFFER
                )
            )
        val mapped = Transformations.map(liveData) { result ->
            val mappedResult: AsyncResult<List<RestaurantSummaryUI>> =
                when (result) {
                    is AsyncResult.Failure -> {
                        AsyncResult.Failure(result.errorMessage)
                    }
                    is AsyncResult.Processing -> {
                        AsyncResult.Processing()
                    }
                    is AsyncResult.Success -> {
                        val mappedList = result.data.map { restaurant ->
                            com.davidgrath.restaurantapptwo.ConverterUtils.restaurantDetailsDataToRestaurantSummaryUI(restaurant)
                        }
                        AsyncResult.Success(mappedList)
                    }
                }
            mappedResult
        }
        return mapped
    }
}