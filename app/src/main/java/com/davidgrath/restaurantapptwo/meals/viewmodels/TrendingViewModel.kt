package com.davidgrath.restaurantapptwo.meals.viewmodels

import androidx.lifecycle.*
import com.davidgrath.restaurantapptwo.main.AsyncResult
import com.davidgrath.restaurantapptwo.meals.MealUseCase
import com.davidgrath.restaurantapptwo.meals.entities.MealSummaryUI
import io.reactivex.rxjava3.core.BackpressureStrategy

class TrendingViewModel(private val useCase: MealUseCase) : ViewModel() {

    fun getTrendingMeals(): LiveData<AsyncResult<List<MealSummaryUI>>> {
        val liveData =
            LiveDataReactiveStreams.fromPublisher(useCase.getTrendingMeals().toFlowable(
                BackpressureStrategy.BUFFER))
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
}