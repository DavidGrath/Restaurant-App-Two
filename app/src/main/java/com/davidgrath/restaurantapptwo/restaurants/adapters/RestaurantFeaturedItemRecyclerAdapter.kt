package com.davidgrath.restaurantapptwo.restaurants.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.davidgrath.restaurantapptwo.R
import com.davidgrath.restaurantapptwo.databinding.RecyclerviewRestaurantDetailsFeaturedItemBinding
import com.davidgrath.restaurantapptwo.meals.entities.MealSummaryUI

class RestaurantFeaturedItemRecyclerAdapter(val featuredItems : List<MealSummaryUI>) : RecyclerView.Adapter<RestaurantFeaturedItemRecyclerAdapter.RestaurantFeaturedItemViewHolder>() {


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
            Glide.with(holder.itemView.context)
                .load(com.davidgrath.restaurantapptwo.Constants.FOOD_IMAGE_IDS.random())
                .fallback(R.drawable.simple_image_placeholder)
                .into(imageviewRestaurantDetailsFeaturedItemPicture)
        }
    }

    override fun getItemCount(): Int {
        return featuredItems.size
    }

    class RestaurantFeaturedItemViewHolder(val binding : RecyclerviewRestaurantDetailsFeaturedItemBinding) : RecyclerView.ViewHolder(binding.root)
}