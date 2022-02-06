package com.example.restaurantapptwo.restaurants.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapptwo.R
import com.example.restaurantapptwo.databinding.RecyclerviewPopularRestaurantBinding
import com.example.restaurantapptwo.restaurants.entities.RestaurantSummaryUI

class RestaurantRecyclerAdapter(private var restaurants : List<RestaurantSummaryUI>) : RecyclerView.Adapter<RestaurantRecyclerAdapter.RestaurantPopularViewHolder>() {

    val images = arrayOf(
        R.drawable.sample_food_square_1,
        R.drawable.sample_food_square_2,
        R.drawable.sample_food_square_3,
        R.drawable.sample_food_1,
        R.drawable.sample_food_2,
        R.drawable.sample_food_3,
        R.drawable.sample_food_4,
        R.drawable.sample_food_5,
        R.drawable.sample_food_6,
        R.drawable.sample_food_7,
        R.drawable.sample_food_8,
        R.drawable.sample_food_9
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantPopularViewHolder {
        val binding = RecyclerviewPopularRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantPopularViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantPopularViewHolder, position: Int) {
        val restaurant = restaurants[position]
        with(holder.binding) {
            imageviewPopularRestaurantPicture1.setImageResource(images.random())
            imageviewPopularRestaurantPicture2.setImageResource(images.random())
            imageviewPopularRestaurantPicture3.setImageResource(images.random())
            textviewPopularRestaurantTitle.text = restaurant.name
            textviewPopularRestaurantAddress.text = restaurant.address
            textviewPopularRestaurantOpenTime.text = restaurant.openTimeText
            ratingBarPopularRestaurant.rating = restaurant.averageRating
        }
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }

    fun setRestaurants(restaurants: List<RestaurantSummaryUI>) {
        this.restaurants = restaurants
        notifyDataSetChanged()
    }

    class RestaurantPopularViewHolder(val binding : RecyclerviewPopularRestaurantBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}