package com.example.restaurantapptwo.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restaurantapptwo.meals.entities.MealSummaryUI
import com.example.restaurantapptwo.restaurants.entities.RestaurantSummaryUI

class HomeViewModel(private val useCase: HomeUseCase) : ViewModel() {

    private val _searchList = MutableLiveData<List<RestaurantSummaryUI>>(emptyList())
    val searchResults : LiveData<List<RestaurantSummaryUI>> = _searchList

    fun getTrendingMeals() : List<MealSummaryUI> {
        return useCase.getTrendingMeals().map {
        val price = "\u20a6${it.price}.00"
            MealSummaryUI(it.name, price, it.restaurantId, it.restaurantName, it.restaurantRating, it.bookmarked, it.imageUrl)
        }
    }

    fun getMostPopularRestaurant() : RestaurantSummaryUI {
        val restaurant = useCase.getMostPopularRestaurant()!!
        //TODO Time format
        //TODO This snippet appears 4 times across 2 files. Can we do better?
        val hour = restaurant.openTime / 60
        val minute = restaurant.openTime % 60
        val amPm = if(restaurant.openTime/60 > 12) "AM" else "PM"
        val openTimeText = "Open $hour:$minute $amPm"
        return RestaurantSummaryUI(restaurant.name, restaurant.address, openTimeText, restaurant.averageRating, null, null, null)
    }

    fun getLatestFeed() : List<RestaurantSummaryUI> {
        return useCase.getLatestFeed().map {
            //TODO Time format
            val hour = it.openTime / 60
            val minute = it.openTime % 60
            val amPm = if(it.openTime/60 > 12) "AM" else "PM"
            val openTimeText = "Open $hour:$minute $amPm"
            RestaurantSummaryUI(it.name, it.address, openTimeText, it.averageRating, null, null, null)
        }
    }

    fun search(query : String) {
        val results = useCase.searchRestaurants(query, emptyList()).map {
            val hour = it.openTime / 60
            val minute = it.openTime % 60
            val amPm = if(it.openTime/60 > 12) "AM" else "PM"
            val openTimeText = "Open $hour:$minute $amPm"
            RestaurantSummaryUI(it.name, it.address, openTimeText, it.averageRating, null, null, null)
        }
        _searchList.postValue(results)
    }

    fun clearSearch() {
        _searchList.postValue(emptyList())
    }

}