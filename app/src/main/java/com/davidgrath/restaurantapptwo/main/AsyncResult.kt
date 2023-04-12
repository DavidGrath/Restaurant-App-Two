package com.davidgrath.restaurantapptwo.main

sealed class AsyncResult<T> private constructor(){
    class Processing<T>() : AsyncResult<T>()
    class Success<T>(val data : T) : AsyncResult<T>()
    class Failure<T>(val errorMessage: String? = null) : AsyncResult<T>()
}
