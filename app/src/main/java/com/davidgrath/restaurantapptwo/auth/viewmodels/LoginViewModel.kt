package com.davidgrath.restaurantapptwo.auth.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davidgrath.restaurantapptwo.auth.AuthUseCase
import com.davidgrath.restaurantapptwo.auth.entities.ui.LoginFormUI
import com.davidgrath.restaurantapptwo.main.AsyncResult
import io.reactivex.rxjava3.core.BackpressureStrategy

class LoginViewModel(private val useCase: AuthUseCase) : ViewModel() {
    val loginFormUI = LoginFormUI()

    fun login() : LiveData<AsyncResult<Any>> {
        val mutableLiveData = MutableLiveData<Any>()
        val liveData = LiveDataReactiveStreams
            .fromPublisher(useCase.login(loginFormUI.username, loginFormUI.password).toFlowable(BackpressureStrategy.BUFFER))
        return liveData

    }

    fun isLocationSet() : Boolean {
        return useCase.isLocationSet()
    }

}