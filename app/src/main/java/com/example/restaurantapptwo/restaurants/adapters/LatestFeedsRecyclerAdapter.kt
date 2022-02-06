package com.example.restaurantapptwo.restaurants.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapptwo.R
import com.example.restaurantapptwo.databinding.RecyclerviewLatestFeedBinding
import com.example.restaurantapptwo.restaurants.entities.RestaurantSummaryUI
import kotlin.random.Random

class LatestFeedsRecyclerAdapter(private val restaurantFeed : List<RestaurantSummaryUI>) : RecyclerView.Adapter<LatestFeedsRecyclerAdapter.LatestFeedsViewHolder>() {

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
            imageviewLatestFeedPicture.setImageResource(images.random())
        }
    }

    override fun getItemCount(): Int {
        return restaurantFeed.size
    }

    class LatestFeedsViewHolder(val binding : RecyclerviewLatestFeedBinding) : RecyclerView.ViewHolder(binding.root)
}