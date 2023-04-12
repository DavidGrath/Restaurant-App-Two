package com.davidgrath.restaurantapptwo.auth

import com.davidgrath.restaurantapptwo.auth.entities.data.UserData
import com.davidgrath.restaurantapptwo.auth.entities.data.UserDataPrivate
import com.davidgrath.restaurantapptwo.auth.entities.data.UserDataPublic
import com.davidgrath.restaurantapptwo.main.AsyncResult
import com.davidgrath.restaurantapptwo.main.NetworkClient
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class AuthRepository(private val networkClient: NetworkClient, private val storageHelper: AuthStorageHelper) {

    //TODO Store JWT with KeyStore APIs
    fun loginWithUsernameAndPassword(username : String, password : String) : Observable<AsyncResult<Any>> {
        val behaviorSubject : BehaviorSubject<AsyncResult<Any>> =
            BehaviorSubject.createDefault(AsyncResult.Processing())
        networkClient.loginWithUsernameAndPassword(username, password)
            .map{ storageHelper.storeToken(it.token) }
            .subscribe(
                {
                    behaviorSubject.onNext(AsyncResult.Success(""))
                    behaviorSubject.onComplete()
                },
                {
                    behaviorSubject.onNext(AsyncResult.Failure(it.message))
                }
            )
        return behaviorSubject
    }

    fun createAccountWithDetails(username: String, password: String, email: String) : Observable<AsyncResult<Any>> {
        val behaviorSubject : BehaviorSubject<AsyncResult<Any>> =
            BehaviorSubject.createDefault(AsyncResult.Processing())
        networkClient.createAccountWithDetails(username, password, email)
            .map { storageHelper.storeToken(it.token) }
            .subscribe(
                {
                    behaviorSubject.onNext(AsyncResult.Success(it))
                    behaviorSubject.onComplete()
                },
                {
                    behaviorSubject.onNext(AsyncResult.Failure(it.message))
                }
            )
        return behaviorSubject
    }

    fun sendPasswordResetRequest(email : String) : Observable<AsyncResult<Any>> {
        val behaviorSubject : BehaviorSubject<AsyncResult<Any>> =
            BehaviorSubject.createDefault(AsyncResult.Processing())
        networkClient.sendPasswordResetRequest(email)
            .subscribe(
                {
                    behaviorSubject.onNext(AsyncResult.Success(it))
                    behaviorSubject.onComplete()
                },
                {
                    behaviorSubject.onNext(AsyncResult.Failure(it.message))
                }
            )
        return behaviorSubject
    }

    fun verifyPhoneNumberWithOTP(otp : String) : Observable<AsyncResult<Any>>{
        val behaviorSubject : BehaviorSubject<AsyncResult<Any>> =
            BehaviorSubject.createDefault(AsyncResult.Processing())
        networkClient.verifyPhoneNumberWithOTP(otp)
            .subscribe(
                {
                    behaviorSubject.onNext(AsyncResult.Success(it))
                    behaviorSubject.onComplete()
                },
                {
                    behaviorSubject.onNext(AsyncResult.Failure(it.message))
                }
            )
        return behaviorSubject
    }

    fun getUserData() : Observable<AsyncResult<UserData>> {
        val behaviorSubject : BehaviorSubject<AsyncResult<UserData>> =
            BehaviorSubject.createDefault(AsyncResult.Processing())
        networkClient.getUserData()
            .subscribe(
                {
                    val publicData = UserDataPublic(it.publicData.username, it.publicData.email,
                        it.publicData.verified, it.publicData.displayPictureURL, it.publicData.reviews)
                    val privateData = UserDataPrivate(it.privateData.authToken,
                        it.privateData.bookmarks, it.privateData.creditCards, it.privateData.deliveryAddresses, it.privateData.location)
                    val userData = UserData(publicData, privateData)
                    behaviorSubject.onNext(AsyncResult.Success(userData))
                    behaviorSubject.onComplete()
                },
                {
                    behaviorSubject.onNext(AsyncResult.Failure(it.message))
                }
            )
        return behaviorSubject
    }

    fun getAuthToken() : String {
        return storageHelper.getToken()
    }

    fun getLocation() : String {
        return storageHelper.getLocation()
    }

    fun setUserLocation(location : String) {
        storageHelper.setLocation(location)
    }
}