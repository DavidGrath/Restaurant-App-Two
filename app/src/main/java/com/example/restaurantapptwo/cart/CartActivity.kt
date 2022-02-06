package com.example.restaurantapptwo.cart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantapptwo.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {

    lateinit var binding : ActivityCartBinding
    lateinit var viewModel: CartViewModel
    lateinit var adapter: CartItemRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(CartViewModel::class.java)

        val toolbar = binding.toolbarCart
        setSupportActionBar(toolbar)
        title = "Cart"
        // Complicated. Not Priority
//        binding.collapsingToolbarCart.title = "Expanded Cart"

        adapter = CartItemRecyclerAdapter(emptyList())
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCart.layoutManager = layoutManager
        binding.recyclerViewCart.adapter = adapter
        viewModel.cartItems.observe(this) { list ->
            adapter.setCartItems(list)
        }
    }
}