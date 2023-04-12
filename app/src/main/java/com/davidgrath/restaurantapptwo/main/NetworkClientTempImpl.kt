package com.davidgrath.restaurantapptwo.main

import com.davidgrath.restaurantapptwo.auth.entities.network.LoginResponseNetwork
import com.davidgrath.restaurantapptwo.auth.entities.network.UserDataNetwork
import com.davidgrath.restaurantapptwo.auth.entities.network.UserDataNetworkPrivate
import com.davidgrath.restaurantapptwo.auth.entities.network.UserDataNetworkPublic
import com.davidgrath.restaurantapptwo.meals.entities.MealDetailsData
import com.davidgrath.restaurantapptwo.restaurants.entities.RestaurantDetailsData
import com.davidgrath.restaurantapptwo.restaurants.entities.ReviewData
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class NetworkClientTempImpl : NetworkClient {

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
        RestaurantDetailsData(1, "Fadeyi Finest Chao!", "01 Alase Lane Fadeyi Lagos", 8 * 60, true, 4.72F, 243, 0, false, sampleMealData.toTypedArray(), simpleMenu, sampleReviewData.toTypedArray()),
        RestaurantDetailsData(2, "Ajegunle Finest Chao!", "39 Mba Street Ajegunle Apapa", 9 * 60, true, 4F, 43, 100, false, sampleMealData.toTypedArray(), simpleMenu, sampleReviewData.toTypedArray()),
        RestaurantDetailsData(3, "Ikeja Finest Chao!", "14 Adeshina Street Ikeja Lagos", 10 * 60, true, 4.2F, 200, 0, false, sampleMealData.toTypedArray(), simpleMenu, sampleReviewData.toTypedArray()),
        RestaurantDetailsData(4, "Shomolu Finest Chao!", "21 Demola Street Shomolu Lagos", 8 * 60, true, 4.5F, 120, 150, false, sampleMealData.toTypedArray(), simpleMenu, sampleReviewData.toTypedArray()),
        RestaurantDetailsData(5, "Yaba Finest Chao!", "Off University Road Yaba Lagos", 8 * 60, true, 3F, 134, 0, false, sampleMealData.toTypedArray(), simpleMenu, sampleReviewData.toTypedArray()),
        RestaurantDetailsData(6, "Ajah Finest Chao", "Just before Abraham Adesanya road, Ajah, Lagos", 9 * 60, true, 4.72F, 243, 0, false, sampleMealData.toTypedArray(), simpleMenu, sampleReviewData.toTypedArray())
    )

    private val publicData =
        UserDataNetworkPublic("John Williams", "johndoe@example.com", true, null, emptyList())
    private val privateData = UserDataNetworkPrivate("abcd", emptyList(), emptyList(), emptyList())
    private val sampleUser = UserDataNetwork(
        publicData, privateData
    )


    //TODO For now all methods return Single<String>
    override fun loginWithUsernameAndPassword(username: String, password: String): Single<LoginResponseNetwork> {
        val random = Random.nextInt(6)
        if (random == 0) {
            return Single.error<LoginResponseNetwork>(Exception("Error. Password Invalid!"))
                .delay(3, TimeUnit.SECONDS, true)
        } else {
            return Single.just(LoginResponseNetwork("abcd", sampleUser))
                .delay(3, TimeUnit.SECONDS)
        }
    }

    override fun createAccountWithDetails(username: String, password: String, email: String): Single<LoginResponseNetwork> {
        val random = Random.nextInt(6)
        if (random == 0) {
            return Single.error<LoginResponseNetwork>(Exception("Username already exists!"))
                .delay(3, TimeUnit.SECONDS, true)
        } else {
            return Single.just(LoginResponseNetwork("abcd", sampleUser))
                .delay(3, TimeUnit.SECONDS)
        }
    }

    override fun sendPasswordResetRequest(email: String): Single<Any> {
        return Single.just(0)
    }

    override fun verifyPhoneNumberWithOTP(otp: String): Single<Any> {
        return Single.just(0)
    }

    override fun getUserData() : Single<UserDataNetwork> {
        return Single.just(sampleUser)
            .delay(2, TimeUnit.SECONDS)
    }

    override fun tokenizeCard(cardHolderName: String, cardNumber: String, expiryDate: String, cvv: String): Single<String> {
        return Single.just("abcd")
            .delay(3, TimeUnit.SECONDS)
    }

    override fun chargeCard(amount : Float, token: String): Single<String> {
        return Single.just("")
            .delay(3, TimeUnit.SECONDS)
    }

    override fun getMostPopularRestaurants(): Single<List<RestaurantDetailsData>> {
        return Single.just(restaurantsSampleData)
    }

    override fun getTrendingMeals(): Single<List<MealDetailsData>> {
        return Single.just(sampleMealData)
    }

    override fun getRestaurantDetails(id: Int): Single<RestaurantDetailsData> {
        return Single.just(restaurantsSampleData.find { it.id == id })
    }

    override fun getLatestFeed(): Single<List<RestaurantDetailsData>> {
        return Single.just(restaurantsSampleData)
    }

    private val searchObservable : BehaviorSubject<List<RestaurantDetailsData>> = BehaviorSubject.create()
    override fun searchRestaurants(query: String, options: Map<String, String?>?): Observable<List<RestaurantDetailsData>> {
        var copy : List<RestaurantDetailsData> = ArrayList(restaurantsSampleData)
        if(query.isNotEmpty()) {
            copy = copy.filter {
                it.name.contains(query)
            }
        }
        options?.let {

            val openNow = it["openNow"]
            if(openNow.toBoolean()) {
                copy = copy.filter {
                    it.openNow
                }
            }
            val creditCards = it["creditCards"]
            if(creditCards.toBoolean()) {
                copy = copy.filter {
                    creditCards.toBoolean()
                }
            }
            val alcoholServed = it["alcoholServed"]
            if(alcoholServed.toBoolean()) {
                copy = copy.filter {
                    alcoholServed.toBoolean()
                }
            }
            val minPrice = it["minPrice"]
            if(minPrice != null) {
                copy = copy.filter {
                    100 >= minPrice.toInt()
                }
            }
            val maxPrice = it["maxPrice"]
            if(maxPrice != null) {
                copy = copy.filter {
                    1_000_000 < maxPrice.toInt()
                }
            }
            val sortBy = it["sortBy"]
            if(sortBy != null) {
                when(sortBy) {
                    //Default
                    "nearestMe", "costDescending", "costAscending", "mostPopular" -> {
                        copy = copy.sortedBy { it.id }
                    }
                    "topRated" -> {
                        copy = copy.sortedByDescending { it.averageRating }
                    }

                }
            }
        }
        searchObservable.onNext(copy)
        return searchObservable
    }
}