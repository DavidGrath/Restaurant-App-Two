package com.davidgrath.restaurantapptwo.auth

import com.davidgrath.restaurantapptwo.auth.entities.data.UserData
import io.reactivex.rxjava3.core.Observable

interface AuthStorageHelper {
    fun storeToken(token : String)
    fun getToken() : String
    fun setLocation(location : String)
    fun getLocation(): String
    //Assumed it is new data
    fun storeUserData(userData: UserData)
    fun getStoredUserData() : Observable<UserData>
    fun getBookmarks() : Observable<List<String>>
}