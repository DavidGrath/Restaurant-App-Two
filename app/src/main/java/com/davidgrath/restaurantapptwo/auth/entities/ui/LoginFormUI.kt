package com.davidgrath.restaurantapptwo.auth.entities.ui

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class LoginFormUI : BaseObservable() {
    @Bindable
    var username = ""
    set(value) {
        //Two-way
        if(field != value) {
            field = value
            if(field.isNotEmpty()) {
                error = null
            }
            notifyPropertyChanged(com.davidgrath.restaurantapptwo.BR.username)
        }
    }
    @Bindable
    var password = ""
    set(value) {
        //Two-way
        if(field != value) {
            field = value
            error = null
            notifyPropertyChanged(com.davidgrath.restaurantapptwo.BR.password)
        }
    }

    @Bindable
    var error : String? = null
    set(value) {
        //Not two-way
        field = value
        notifyPropertyChanged(com.davidgrath.restaurantapptwo.BR.error)
    }

}