package com.davidgrath.restaurantapptwo.restaurants.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.davidgrath.restaurantapptwo.main.AsyncResult
import com.davidgrath.restaurantapptwo.meals.entities.MealSummaryUI
import com.davidgrath.restaurantapptwo.restaurants.RestaurantUseCase
import com.davidgrath.restaurantapptwo.restaurants.entities.RestaurantDetailsUI
import com.davidgrath.restaurantapptwo.restaurants.entities.ReviewUI
import io.reactivex.rxjava3.core.BackpressureStrategy

class RestaurantDetailsViewModel(val useCase: RestaurantUseCase) : ViewModel() {

    fun getRestaurantDetails(id : Int) : LiveData<AsyncResult<RestaurantDetailsUI>> {
        //TODO Error cases and whatnot
        val liveData =
            LiveDataReactiveStreams.fromPublisher(useCase.getRestaurantDetails(id).toFlowable(BackpressureStrategy.BUFFER))
        val mappedLiveData = Transformations.map(liveData) { result ->
            val mappedResult : AsyncResult<RestaurantDetailsUI> =
                when(result) {
                    is AsyncResult.Failure -> {
                        AsyncResult.Failure(result.errorMessage)
                    }
                    is AsyncResult.Processing -> {
                        AsyncResult.Processing()
                    }
                    is AsyncResult.Success -> {
                        val data = result.data!!
                        val deliveryCostText = if(data.deliveryCost > 0) "\u20a6${data.deliveryCost}" else "Free"
                        val featuredItems = data.featuredItems.map {
                            //TODO Decimal Format
                            val price = "\u20a6${it.price}.00"
                            MealSummaryUI(it.name, price, it.restaurantId, it.restaurantName, it.restaurantRating, it.bookmarked, it.imageUrl)
                        }
                        //TODO Time format
                        val relativeTime = "2 hours ago"
                        val reviews = data.reviews.map { ReviewUI(it.username, it.userImageUrl, it.rating, it.reviewText, relativeTime) }
                        val detailsUI = RestaurantDetailsUI(data.name, data.address, data.openTime, data.openNow, data.averageRating,
                            data.reviewCount, deliveryCostText, data.bookmarked, featuredItems, data.menu, reviews)
                        AsyncResult.Success(detailsUI)
                    }
                }
            mappedResult
        }
        return mappedLiveData
    }
}