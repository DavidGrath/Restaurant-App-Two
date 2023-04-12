package com.davidgrath.restaurantapptwo.restaurants

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.davidgrath.restaurantapptwo.R
import com.davidgrath.restaurantapptwo.databinding.ActivityRestaurantDetailsBinding
import com.davidgrath.restaurantapptwo.main.AsyncResult
import com.davidgrath.restaurantapptwo.restaurants.adapters.RestaurantFeaturedItemRecyclerAdapter
import com.davidgrath.restaurantapptwo.restaurants.adapters.RestaurantMenuRecyclerAdapter
import com.davidgrath.restaurantapptwo.restaurants.adapters.RestaurantReviewRecyclerAdapter
import com.davidgrath.restaurantapptwo.restaurants.viewmodels.RestaurantDetailsViewModel
import com.davidgrath.restaurantapptwo.restaurants.viewmodels.factories.RestaurantDetailsViewModelFactory

class RestaurantDetailsActivity : AppCompatActivity() {

    lateinit var binding : ActivityRestaurantDetailsBinding
    lateinit var viewModel : RestaurantDetailsViewModel
    lateinit var featuredItemsRecyclerAdapter : RestaurantFeaturedItemRecyclerAdapter
    lateinit var menuRecyclerAdapter : RestaurantMenuRecyclerAdapter
    lateinit var reviewRecyclerAdapter: RestaurantReviewRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = resources.getColor(R.color.white)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
        viewModel = ViewModelProvider(this,
            RestaurantDetailsViewModelFactory((application as com.davidgrath.restaurantapptwo.application.RestaurantAppTwo).getRestaurantUseCase()))
            .get(RestaurantDetailsViewModel::class.java)
        //TODO Handle null savedInstanceState, intent extras, etc edge cases
        val bundle = intent.extras
        val restaurantId = bundle?.getInt(com.davidgrath.restaurantapptwo.Constants.Extras.RESTAURANT_DETAILS_RESTAURANT_ID) ?: 1
        viewModel.getRestaurantDetails(restaurantId).observe(this) { result ->
            when(result) {
                is AsyncResult.Failure -> {
                    Toast.makeText(this, result.errorMessage, Toast.LENGTH_LONG).show()
                }
                is AsyncResult.Processing -> {
                    Toast.makeText(this, "Just wait...", Toast.LENGTH_LONG).show()
                }
                is AsyncResult.Success -> {
                    val restaurantDetails = result.data
                    featuredItemsRecyclerAdapter = RestaurantFeaturedItemRecyclerAdapter(restaurantDetails.featuredItems)
                    val featuredItemsLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                    binding.recyclerViewRestaurantDetailsFeatured.adapter = featuredItemsRecyclerAdapter
                    binding.recyclerViewRestaurantDetailsFeatured.layoutManager = featuredItemsLayoutManager

                    menuRecyclerAdapter = RestaurantMenuRecyclerAdapter(restaurantDetails.menu.toList())
                    val menuLayoutManager = LinearLayoutManager(this)
                    binding.recyclerViewRestaurantDetailsMenu.adapter = menuRecyclerAdapter
                    binding.recyclerViewRestaurantDetailsMenu.layoutManager = menuLayoutManager

                    reviewRecyclerAdapter = RestaurantReviewRecyclerAdapter(restaurantDetails.reviews)
                    val reviewLayoutManager = LinearLayoutManager(this)
                    binding.recyclerViewRestaurantDetailsReviews.adapter = reviewRecyclerAdapter
                    binding.recyclerViewRestaurantDetailsReviews.layoutManager = reviewLayoutManager
                }
            }
        }
    }
}