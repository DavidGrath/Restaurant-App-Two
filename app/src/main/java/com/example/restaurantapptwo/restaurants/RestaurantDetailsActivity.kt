package com.example.restaurantapptwo.restaurants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantapptwo.Constants
import com.example.restaurantapptwo.application.RestaurantAppTwo
import com.example.restaurantapptwo.databinding.ActivityRestaurantDetailsBinding
import com.example.restaurantapptwo.restaurants.adapters.RestaurantFeaturedItemRecyclerAdapter
import com.example.restaurantapptwo.restaurants.adapters.RestaurantMenuRecyclerAdapter
import com.example.restaurantapptwo.restaurants.adapters.RestaurantReviewRecyclerAdapter
import com.example.restaurantapptwo.restaurants.viewmodels.RestaurantDetailsViewModel
import com.example.restaurantapptwo.restaurants.viewmodels.factories.RestaurantDetailsViewModelFactory

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
        viewModel = ViewModelProvider(this,
            RestaurantDetailsViewModelFactory((application as RestaurantAppTwo).getRestaurantUseCase()))
            .get(RestaurantDetailsViewModel::class.java)
        //TODO Handle null savedInstanceState, intent extras, etc edge cases
        val bundle = intent.extras
        val restaurantId = bundle?.getInt(Constants.Extras.RESTAURANT_DETAILS_RESTAURANT_ID) ?: 1
        val restaurantDetails = viewModel.getRestaurantDetails(restaurantId)
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