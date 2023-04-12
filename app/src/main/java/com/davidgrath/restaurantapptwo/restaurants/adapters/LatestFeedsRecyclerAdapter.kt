package com.davidgrath.restaurantapptwo.restaurants.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.davidgrath.restaurantapptwo.Constants
import com.davidgrath.restaurantapptwo.R
import com.davidgrath.restaurantapptwo.databinding.RecyclerviewLatestFeedBinding
import com.davidgrath.restaurantapptwo.restaurants.entities.RestaurantSummaryUI

class LatestFeedsRecyclerAdapter(restaurantFeed : List<RestaurantSummaryUI>, var restaurantClickListener : RestaurantClickListener? = null) : RecyclerView.Adapter<LatestFeedsRecyclerAdapter.LatestFeedsViewHolder>() {

    var restaurantFeed : List<RestaurantSummaryUI> = restaurantFeed
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    interface RestaurantClickListener {
        fun onRestaurantClicked(restaurant : RestaurantSummaryUI)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestFeedsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerviewLatestFeedBinding.inflate(inflater, parent, false)
        return LatestFeedsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LatestFeedsViewHolder, position: Int) {
        val restaurant = restaurantFeed[position]
        with(holder.binding) {
            textviewLatestFeedRestaurantTitle.text = restaurant.name
            textviewLatestFeedAddress.text = restaurant.address
            textviewLatestFeedOpenTime.text = restaurant.openTimeText
            ratingBarLatestFeed.rating = restaurant.averageRating
            Glide.with(holder.itemView.context)
                .load(Constants.FOOD_IMAGE_IDS.random())
                .fallback(R.drawable.simple_image_placeholder)
                .into(imageviewLatestFeedPicture)
            root.setOnClickListener{
                restaurantClickListener?.onRestaurantClicked(restaurant)
            }
        }
    }

    override fun getItemCount(): Int {
        return restaurantFeed.size
    }

    class LatestFeedsViewHolder(val binding : RecyclerviewLatestFeedBinding) : RecyclerView.ViewHolder(binding.root)
}