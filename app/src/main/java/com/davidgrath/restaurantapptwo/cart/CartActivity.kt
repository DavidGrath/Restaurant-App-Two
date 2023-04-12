package com.davidgrath.restaurantapptwo.cart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.davidgrath.restaurantapptwo.application.RestaurantAppTwo
import com.davidgrath.restaurantapptwo.cart.viewmodels.CartViewModel
import com.davidgrath.restaurantapptwo.cart.viewmodels.factories.CartViewModelFactory
import com.davidgrath.restaurantapptwo.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {

    lateinit var binding : ActivityCartBinding
    lateinit var viewModel: CartViewModel
    lateinit var adapter: CartItemRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = CartViewModelFactory((application as RestaurantAppTwo).getCartUseCase()).create(CartViewModel::class.java)

        val toolbar = binding.toolbarCart
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        title = "Cart"

        adapter = CartItemRecyclerAdapter(emptyList())
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCart.layoutManager = layoutManager
        binding.recyclerViewCart.adapter = adapter
        viewModel.cartItems.observe(this) { list ->
            adapter.setCartItems(list)
        }
    }
}