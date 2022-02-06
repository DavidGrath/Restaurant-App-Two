package com.example.restaurantapptwo.restaurants.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapptwo.R
import com.example.restaurantapptwo.databinding.RecyclerviewRestaurantDetailsFeaturedItemBinding
import com.example.restaurantapptwo.meals.entities.MealSummaryUI

class RestaurantFeaturedItemRecyclerAdapter(val featuredItems : List<MealSummaryUI>) : RecyclerView.Adapter<RestaurantFeaturedItemRecyclerAdapter.RestaurantFeaturedItemViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantFeaturedItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerviewRestaurantDetailsFeaturedItemBinding.inflate(inflater, parent, false)
        return RestaurantFeaturedItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantFeaturedItemViewHolder, position: Int) {
        val featuredItem = featuredItems[position]
        with(holder.binding) {
            textviewRestaurantDetailsFeaturedItemName.text = featuredItem.name
            textviewRestaurantDetailsFeaturedItemPrice.text = featuredItem.price
            imageviewRestaurantDetailsFeaturedItemPicture.setImageResource(images.random())
        }
    }

    override fun getItemCount(): Int {
        return featuredItems.size
    }

    class RestaurantFeaturedItemViewHolder(val binding : RecyclerviewRestaurantDetailsFeaturedItemBinding) : RecyclerView.ViewHolder(binding.root)
}