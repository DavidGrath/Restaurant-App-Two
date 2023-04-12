package com.davidgrath.restaurantapptwo.auth.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.davidgrath.restaurantapptwo.auth.AuthUseCase
import com.davidgrath.restaurantapptwo.auth.entities.ui.CreateAccountFormUI
import com.davidgrath.restaurantapptwo.main.AsyncResult
import io.reactivex.rxjava3.core.BackpressureStrategy

class CreateAccountViewModel(private val useCase : AuthUseCase) : ViewModel() {

    val createAccountFormUI = CreateAccountFormUI()

    fun signUp() : LiveData<AsyncResult<Any>> {
        val single = useCase
            .createAccount(createAccountFormUI.fullName, createAccountFormUI.password, createAccountFormUI.email)
        val liveData = LiveDataReactiveStreams.fromPublisher(single.toFlowable(BackpressureStrategy.BUFFER))
        return liveData
    }

    fun isLocationSet() : Boolean {
        return useCase.isLocationSet()
    }
}