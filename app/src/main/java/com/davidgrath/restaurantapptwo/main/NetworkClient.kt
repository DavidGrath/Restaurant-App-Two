package com.davidgrath.restaurantapptwo.main

import com.davidgrath.restaurantapptwo.auth.entities.network.LoginResponseNetwork
import com.davidgrath.restaurantapptwo.auth.entities.network.UserDataNetwork
import com.davidgrath.restaurantapptwo.meals.entities.MealDetailsData
import com.davidgrath.restaurantapptwo.restaurants.entities.RestaurantDetailsData
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface NetworkClient {
    //TODO Store JWT with KeyStore APIs
    fun loginWithUsernameAndPassword(username : String, password : String) : Single<LoginResponseNetwork>
    //TODO Resolve Filler parameter
    fun createAccountWithDetails(username: String, password: String, email: String) : Single<LoginResponseNetwork>
    fun sendPasswordResetRequest(email : String) : Single<Any>
    fun verifyPhoneNumberWithOTP(otp : String) : Single<Any>
    fun getUserData() : Single<UserDataNetwork>
    fun tokenizeCard(cardHolderName : String, cardNumber : String, expiryDate : String, cvv : String) : Single<String>
    fun chargeCard(amount : Float, token : String) : Single<String>
    fun getMostPopularRestaurants() : Single<List<RestaurantDetailsData>>
    fun getTrendingMeals() : Single<List<MealDetailsData>>
    fun getLatestFeed() : Single<List<RestaurantDetailsData>>
    fun getRestaurantDetails(id : Int) : Single<RestaurantDetailsData>
    fun searchRestaurants(query : String, options : Map<String, String?>?) : Observable<List<RestaurantDetailsData>>
}