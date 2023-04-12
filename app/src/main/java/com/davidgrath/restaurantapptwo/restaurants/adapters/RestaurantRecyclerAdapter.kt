package com.davidgrath.restaurantapptwo.restaurants.adapters

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.davidgrath.restaurantapptwo.R
import com.davidgrath.restaurantapptwo.databinding.RecyclerviewPopularRestaurantBinding
import com.davidgrath.restaurantapptwo.restaurants.entities.RestaurantSummaryUI

class RestaurantRecyclerAdapter(restaurants : List<RestaurantSummaryUI>) : RecyclerView.Adapter<RestaurantRecyclerAdapter.RestaurantPopularViewHolder>() {

    var restaurants : List<RestaurantSummaryUI> = restaurants
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantPopularViewHolder {
        val binding = RecyclerviewPopularRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantPopularViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantPopularViewHolder, position: Int) {
        val restaurant = restaurants[position]
        val context = holder.itemView.context
        with(holder.binding) {
            val resources = context.resources
            Glide.with(context)
                .asBitmap()
                .placeholder(R.drawable.simple_image_placeholder)
                .fallback(R.drawable.simple_image_placeholder)
                .centerCrop()
                .load(com.davidgrath.restaurantapptwo.Constants.FOOD_IMAGE_IDS.random())
                .into(object : BitmapImageViewTarget(imageviewPopularRestaurantPicture1) {
                    override fun setResource(resource: Bitmap?) {
                        resource?.let {
                           val roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(resources, it)
                           roundedBitmapDrawable.cornerRadius = resources.getDimension(R.dimen.space_size_1)
                           view.setImageDrawable(roundedBitmapDrawable)
                        }
                    }
                })
            Glide.with(context)
                .asBitmap()
                .placeholder(R.drawable.simple_image_placeholder)
                .fallback(R.drawable.simple_image_placeholder)
                .centerCrop()
                .load(com.davidgrath.restaurantapptwo.Constants.FOOD_IMAGE_IDS.random())
                .into(object : BitmapImageViewTarget(imageviewPopularRestaurantPicture2) {
                    override fun setResource(resource: Bitmap?) {
                        resource?.let {
                           val roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(resources, it)
                           roundedBitmapDrawable.cornerRadius = resources.getDimension(R.dimen.space_size_1)
                           view.setImageDrawable(roundedBitmapDrawable)
                        }
                    }
                })
            Glide.with(context)
                .asBitmap()
                .placeholder(R.drawable.simple_image_placeholder)
                .fallback(R.drawable.simple_image_placeholder)
                .centerCrop()
                .load(com.davidgrath.restaurantapptwo.Constants.FOOD_IMAGE_IDS.random())
                .into(object : BitmapImageViewTarget(imageviewPopularRestaurantPicture3) {
                    override fun setResource(resource: Bitmap?) {
                        resource?.let {
                           val roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(resources, it)
                           roundedBitmapDrawable.cornerRadius = resources.getDimension(R.dimen.space_size_1)
                           view.setImageDrawable(roundedBitmapDrawable)
                        }
                    }
                })
            textviewPopularRestaurantTitle.text = restaurant.name
            textviewPopularRestaurantAddress.text = restaurant.address
            textviewPopularRestaurantOpenTime.text = restaurant.openTimeText
            ratingBarPopularRestaurant.rating = restaurant.averageRating
        }
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }

    class RestaurantPopularViewHolder(val binding : RecyclerviewPopularRestaurantBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}