package com.example.restaurantapptwo.restaurants.data

import com.example.restaurantapptwo.meals.entities.MealDetailsData
import com.example.restaurantapptwo.restaurants.entities.RestaurantDetailsData
import com.example.restaurantapptwo.restaurants.entities.ReviewData
import java.util.*
import kotlin.collections.HashSet

class RestaurantRepository {
    private val restaurantBookmarks = HashSet<Int>()
    val time = Date().time
    private val sampleReviewData = listOf(
        ReviewData("John Jones", null, 4, "My niece, Megan Jones, recommended this place to me. I love what she introduced me to. The only problem I have, though, is the open barbecue fires outside", time),
        ReviewData("Mary Sue", null, 1, "I'm the definition of perfection. You're not. Deal with it", time),
        ReviewData("Bruce Wayne", null, 4, "Buy BATCOIN", time),
        ReviewData("Maui Hawaii", null, 5, "Thank you", time),
        ReviewData("Dennis Menace", null, 3, "Hopefully I don't get a reboot. No old cartoon is safe nowadays.", time),
    )
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
    private val simpleMenu = mapOf(
        "Popular Items" to 10,
        "Beef" to 3,
        "Chicken" to 5,
        "Soups" to 6,
        "Vegetables" to 7,
        "Noodles" to 4,
        "Drink" to 5
    )
    private val restaurantsSampleData = listOf(
        RestaurantDetailsData(1, "Fadeyi Finest Chao!", "01 Alase Lane Fadeyi Lagos", 8 * 60, 4.72F, 243, 0, false, sampleMealData.toTypedArray(), simpleMenu, sampleReviewData.toTypedArray()),
        RestaurantDetailsData(2, "Ajegunle Finest Chao!", "39 Mba Street Ajegunle Apapa", 9 * 60, 4F, 43, 100, false, sampleMealData.toTypedArray(), simpleMenu, sampleReviewData.toTypedArray()),
        RestaurantDetailsData(3, "Ikeja Finest Chao!", "14 Adeshina Street Ikeja Lagos", 10 * 60, 4.2F, 200, 0, false, sampleMealData.toTypedArray(), simpleMenu, sampleReviewData.toTypedArray()),
        RestaurantDetailsData(4, "Shomolu Finest Chao!", "21 Demola Street Shomolu Lagos", 8 * 60, 4.5F, 120, 150, false, sampleMealData.toTypedArray(), simpleMenu, sampleReviewData.toTypedArray()),
        RestaurantDetailsData(5, "Yaba Finest Chao!", "Off University Road Yaba Lagos", 8 * 60, 3F, 134, 0, false, sampleMealData.toTypedArray(), simpleMenu, sampleReviewData.toTypedArray()),
        RestaurantDetailsData(6, "Ajah Finest Chao", "Just before Abraham Adesanya road, Ajah, Lagos", 9 * 60, 4.72F, 243, 0, false, sampleMealData.toTypedArray(), simpleMenu, sampleReviewData.toTypedArray())
    )
    fun addBookmark(id: Int) {
        restaurantBookmarks.add(id)
    }

    fun removeBookmark(id : Int) {
        restaurantBookmarks.remove(id)
    }

    fun getMostPopularRestaurants() : List<RestaurantDetailsData>{
        return restaurantsSampleData.map {
            it.bookmarked = it.id in restaurantBookmarks
            it
        }
    }

    fun getLatestFeed() : List<RestaurantDetailsData>{
        return restaurantsSampleData.map {
            it.bookmarked = it.id in restaurantBookmarks
            it
        }
    }

    fun getRestaurantDetails(id : Int) : RestaurantDetailsData? {
        return restaurantsSampleData.find { it.id == id }
    }

    fun searchRestaurants(query : String) : List<RestaurantDetailsData> {
        return restaurantsSampleData.filter { it.name.contains(query, true) }
    }
}