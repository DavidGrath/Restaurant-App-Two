package com.example.restaurantapptwo.restaurants.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapptwo.databinding.RecyclerviewRestaurantDetailsReviewBinding
import com.example.restaurantapptwo.restaurants.entities.ReviewUI
import kotlin.random.Random

class RestaurantReviewRecyclerAdapter(val reviews : List<ReviewUI>) : RecyclerView.Adapter<RestaurantReviewRecyclerAdapter.RestaurantReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerviewRestaurantDetailsReviewBinding.inflate(inflater, parent, false)
        return RestaurantReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantReviewViewHolder, position: Int) {
        val review = reviews[position]
        with(holder.binding) {
            textviewRestaurantDetailsReviewItemUsername.text = review.username
            textviewRestaurantDetailsReviewItemReview.text = review.reviewText
            textviewRestaurantDetailsReviewItemRelativeTime.text = review.relativeTime
            ratingBarRestaurantDetailsReviewItem.rating = review.rating.toFloat()
        }
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    class RestaurantReviewViewHolder(val binding : RecyclerviewRestaurantDetailsReviewBinding) : RecyclerView.ViewHolder(binding.root)
}