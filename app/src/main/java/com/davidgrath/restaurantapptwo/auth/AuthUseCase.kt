package com.davidgrath.restaurantapptwo.auth

import com.davidgrath.restaurantapptwo.main.AsyncResult
import com.davidgrath.restaurantapptwo.main.UseCase
import io.reactivex.rxjava3.core.Observable

class AuthUseCase(private val repository: AuthRepository) :
    UseCase {

    fun login(username : String, password : String) : Observable<AsyncResult<Any>> {
        return repository.loginWithUsernameAndPassword(username, password)
    }

    fun createAccount(username: String, password: String, email : String) : Observable<AsyncResult<Any>> {
        return repository.createAccountWithDetails(username, password, email)
    }

    fun authTokenExists() : Boolean {
        return repository.getAuthToken().isNotEmpty()
    }

    fun isLocationSet() : Boolean {
        return repository.getLocation().isNotEmpty()
    }

    fun setLocation(location : String) {
        repository.setUserLocation(location)
    }
}