package com.davidgrath.restaurantapptwo.restaurants.search

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

//TODO Later
class SortFilterFormUI(
    sortBy : Boolean
) : BaseObservable() {

    @Bindable
    var sortBy = sortBy
    set(value) {
        field = value
//        notifyPropertyChanged(BR.so)
    }
}