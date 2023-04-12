package com.davidgrath.restaurantapptwo.meals

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.davidgrath.restaurantapptwo.R
import com.davidgrath.restaurantapptwo.application.RestaurantAppTwo
import com.davidgrath.restaurantapptwo.cart.CartItemRecyclerAdapter
import com.davidgrath.restaurantapptwo.cart.CheckoutActivity
import com.davidgrath.restaurantapptwo.databinding.ActivityMealOrderBinding
import com.davidgrath.restaurantapptwo.meals.viewmodels.MealOrderViewModel
import com.davidgrath.restaurantapptwo.meals.viewmodels.factories.MealOrderViewModelFactory

class MealOrderActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding : ActivityMealOrderBinding
    lateinit var viewModel : MealOrderViewModel
    lateinit var adapter : CartItemRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this,
            MealOrderViewModelFactory((application as RestaurantAppTwo).getMealUseCase())
        ).get(MealOrderViewModel::class.java)
        binding.buttonMealOrderCheckout.setOnClickListener(this)
        adapter = CartItemRecyclerAdapter(emptyList())
        viewModel.getCartItems().observe(this) { newList ->
            adapter.setCartItems(newList)
        }
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