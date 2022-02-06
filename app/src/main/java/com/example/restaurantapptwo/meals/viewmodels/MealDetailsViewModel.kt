package com.example.restaurantapptwo.meals.viewmodels

import androidx.lifecycle.ViewModel
import com.example.restaurantapptwo.meals.entities.MealDetailsData
import com.example.restaurantapptwo.meals.entities.MealDetailsUI
import com.example.restaurantapptwo.restaurants.RestaurantUseCase

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
        MealDetailsData(1, "Crispy Chicken San", 1, "Fadeyi Finest Chao", 4.72F, "It's food. What else do you wanna know, huh?!?", mealExtras, false, 5000, null),
        MealDetailsData(2, "Salad Fritters", 1, "Fadeyi Finest Chao", 4.72F, "It's food. What else do you wanna know, huh?!?", mealExtras, false, 5000, null),
        MealDetailsData(3, "Braised Fish Head", 1, "Fadeyi Finest Chao", 4.72F, "It's food. What else do you wanna know, huh?!?", mealExtras, false, 5000, null),
        MealDetailsData(4, "Spicy & Sour Clams", 1, "Fadeyi Finest Chao", 4.72F, "It's food. What else do you wanna know, huh?!?", mealExtras, false, 5000, null)
    )
    var orderQuantity = 1
    private set
    fun getMealDetails() : MealDetailsUI {
        val sampleMeal = sampleMealData[0]
        val price = "\u20a6${sampleMeal.price}.00"
        val extras = sampleMeal.extras
            .mapValues { p -> "\u20a6${p}.00" }
        return MealDetailsUI(sampleMeal.id, sampleMeal.name, sampleMeal.restaurantId, sampleMeal.restaurantName, sampleMeal.restaurantRating, sampleMeal.description, extras, sampleMeal.bookmarked, price, null)
    }

    fun incrementQuantity() {
        if(orderQuantity + 1 <= 100) {
            orderQuantity++
        }
    }

    fun decrementQuantity() {
        if(orderQuantity - 1 >= 0) {
            orderQuantity--
        }
    }
}