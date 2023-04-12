package com.davidgrath.restaurantapptwo.auth.entities.ui

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class CreateAccountFormUI : BaseObservable() {

    @Bindable
    var fullName = ""
    set(value) {
        if(field != value) {
            field = value
            if(field.isNotEmpty()) {
                error = null
            }
            notifyPropertyChanged(com.davidgrath.restaurantapptwo.BR.fullName)
        }
    }

    @Bindable
    var email = ""
    set(value) {
        if(field != value) {
            field = value
            if(field.isNotEmpty()) {
                error = null
            }
            notifyPropertyChanged(com.davidgrath.restaurantapptwo.BR.email)
        }
    }

    @Bindable
    var password = ""
    set(value) {
        if(field != value) {
            field = value
            notifyPropertyChanged(com.davidgrath.restaurantapptwo.BR.password)
        }
    }

    @Bindable
    var passwordConfirm = ""
    set(value) {
        if(field != value) {
            field = value
            notifyPropertyChanged(com.davidgrath.restaurantapptwo.BR.passwordConfirm)
        }
    }

    @Bindable
    var error : String? = null
    set(value) {
        field = value
        notifyPropertyChanged(com.davidgrath.restaurantapptwo.BR.error)
    }
}