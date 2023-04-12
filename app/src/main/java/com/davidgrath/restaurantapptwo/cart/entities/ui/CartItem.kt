package com.davidgrath.restaurantapptwo.cart.entities.ui

data class CartItem(
    val name : String,
    val description : String,
    val price : Int,
    val imageURL : String?
) {
}