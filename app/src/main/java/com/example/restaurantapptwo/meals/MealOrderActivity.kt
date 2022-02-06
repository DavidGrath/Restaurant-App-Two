package com.example.restaurantapptwo.meals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantapptwo.cart.CheckoutActivity
import com.example.restaurantapptwo.R
import com.example.restaurantapptwo.application.CartItem
import com.example.restaurantapptwo.cart.CartItemRecyclerAdapter
import com.example.restaurantapptwo.cart.CartItemUI
import com.example.restaurantapptwo.databinding.ActivityMealOrderBinding

class MealOrderActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding : ActivityMealOrderBinding
    lateinit var adapter : CartItemRecyclerAdapter

    val tempList = listOf(
        CartItem("Bread Egg Delight", "2x bread loaf, 3x egg yolk, 1x green tea", 2_000, null),
        CartItem("Suya Beef Surprise", "2x gizzard, 3x beef, 1x chicken", 10_000, null),
        CartItem("Don't", "???", 0, null),
        CartItem("Amala", "2x amala, 1x egusi, 7x beef", 500, null),
        CartItem("Indomie", "5x Indomie", 1_000, null),
        CartItem("Bread Egg Delight", "2x bread loaf, 3x egg yolk, 1x green tea", 2_000, null),
        CartItem("Suya Beef Surprise", "2x gizzard, 3x beef, 1x chicken", 10_000, null),
        CartItem("Don't", "???", 0, null),
        CartItem("Amala", "2x amala, 1x egusi, 7x beef", 500, null),
        CartItem("Indomie", "5x Indomie", 1_000, null)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonMealOrderCheckout.setOnClickListener(this)
        adapter = CartItemRecyclerAdapter(tempList.map { it -> CartItemUI(it.name, it.description, it.price, it.imageURL) })
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewMealOrder.adapter = adapter
        binding.recyclerViewMealOrder.layoutManager = layoutManager
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_meal_order, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menuitem_meal_order_close -> {
                //TODO Close
            }
        }
        return true
    }

    override fun onClick(v: View?) {
        v?.let {
            when(it) {
                binding.buttonMealOrderCheckout -> {
                    val checkoutIntent = Intent(this, CheckoutActivity::class.java)
                    startActivity(checkoutIntent)
                }
            }
        }
    }
}