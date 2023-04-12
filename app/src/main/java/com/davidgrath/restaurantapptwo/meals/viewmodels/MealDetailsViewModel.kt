package com.davidgrath.restaurantapptwo.meals.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davidgrath.restaurantapptwo.meals.entities.MealDetailsData
import com.davidgrath.restaurantapptwo.meals.entities.MealDetailsUI
import com.davidgrath.restaurantapptwo.restaurants.RestaurantUseCase

//TODO Fix constructor
class MealDetailsViewModel(private val useCase: RestaurantUseCase) : ViewModel() {

    //TODO These lists are duplicates of the ones found in RestaurantRepository.kt. Revise to make them unique
    private val mealExtras = mapOf<String, Int>(
        "Sauce" to 150,
        "Salmon" to 80,
        "Salad" to 200,
        "Unagi" to 50,
        "Vegetables" to 300,
        "Noodles" to 400,
        "Sauce" to 150,
        "Salmon" to 80,
        "Salad" to 200
    )
    private val sampleMealData = listOf(
        MealDetailsData(1, "Crispy Chicken San", 1, "Fadeyi Finest Chao", 4.72F, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut accumsan purus ut placerat lobortis. Donec magna dolor, vulputate et orci ac, ultrices egestas quam. Mauris libero nisi, porta accumsan lorem eget, commodo venenatis tortor. In mattis sapien a eros sodales bibendum. Quisque eleifend dui sed luctus interdum. Curabitur feugiat vehicula quam, et semper ipsum finibus quis. Vestibulum viverra sed augue eu lacinia. Donec egestas lacus quis quam venenatis lacinia. Nulla ut mi eu ante euismod imperdiet quis id lacus. Donec vitae ullamcorper orci. Suspendisse commodo tellus ipsum, quis lobortis ipsum tempus at. Nam a ultrices sapien. Sed maximus lorem vitae nibh volutpat, sit amet laoreet augue elementum. Integer suscipit odio sed dui condimentum mattis. Sed sollicitudin hendrerit nunc vel tempor.", mealExtras, false, 5000, null),
        MealDetailsData(2, "Salad Fritters", 1, "Fadeyi Finest Chao", 4.72F, "It's food. What else do you wanna know, huh?!?", mealExtras, false, 5000, null),
        MealDetailsData(3, "Braised Fish Head", 1, "Fadeyi Finest Chao", 4.72F, "It's food. What else do you wanna know, huh?!?", mealExtras, false, 5000, null),
        MealDetailsData(4, "Spicy & Sour Clams", 1, "Fadeyi Finest Chao", 4.72F, "It's food. What else do you wanna know, huh?!?", mealExtras, false, 5000, null)
    )

    private val _orderTotalLiveData : MutableLiveData<Int>
    val orderTotalLiveData : LiveData<Int>

    private var orderQuantity = 1
    private val _orderQuantityLiveData = MutableLiveData<Int>(orderQuantity)
    val orderQuantityLiveData : LiveData<Int> = _orderQuantityLiveData

    init {
        _orderTotalLiveData = MutableLiveData<Int>(sampleMealData[0].price)
        orderTotalLiveData = _orderTotalLiveData
    }

    fun getMealDetails() : MealDetailsUI {
        val sampleMeal = sampleMealData[0]
        val price = "\u20a6${sampleMeal.price}.00"
        val extras = sampleMeal.extras
            .mapValues { entry -> "\u20a6${entry.value}.00" }
        return MealDetailsUI(sampleMeal.id, sampleMeal.name, sampleMeal.restaurantId, sampleMeal.restaurantName, sampleMeal.restaurantRating, sampleMeal.description, extras, sampleMeal.bookmarked, price, null)
    }

    fun incrementQuantity() {
        if(orderQuantity + 1 <= 100) {
            orderQuantity++
            _orderQuantityLiveData.postValue(orderQuantity)
            _orderTotalLiveData.postValue(orderQuantity * sampleMealData[0].price)
        }
    }

    fun decrementQuantity() {
        if(orderQuantity - 1 >= 0) {
            orderQuantity--
            _orderQuantityLiveData.postValue(orderQuantity)
            _orderTotalLiveData.postValue(orderQuantity * sampleMealData[0].price)
        }
    }
}