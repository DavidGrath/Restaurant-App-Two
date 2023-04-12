package com.davidgrath.restaurantapptwo.cart.entities.ui

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class CheckoutFormUI : BaseObservable() {

    @Bindable
    var deliveryAddress: String = ""
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(com.davidgrath.restaurantapptwo.BR.deliveryAddress)
            }
        }

    @Bindable
    var cardNumber: String = ""
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(com.davidgrath.restaurantapptwo.BR.cardNumber)
            }
        }

    @Bindable
    var cardHolderName: String = ""
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(com.davidgrath.restaurantapptwo.BR.cardHolderName)
            }
        }

    @Bindable
    var expiryDate: String = ""
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(com.davidgrath.restaurantapptwo.BR.expiryDate)
            }
        }

    @Bindable
    var cvv: String = ""
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(com.davidgrath.restaurantapptwo.BR.cvv)
            }
        }

    //One-way binding
    @Bindable
    var saveCard = false
        set(value) {
            field = value
            notifyPropertyChanged(com.davidgrath.restaurantapptwo.BR.saveCard)
        }

    //One-way binding
    @Bindable
    var error: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(com.davidgrath.restaurantapptwo.BR.error)

        }
}